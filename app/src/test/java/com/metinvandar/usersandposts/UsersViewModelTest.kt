package com.metinvandar.usersandposts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.metinvandar.usersandposts.domain.Resource
import com.metinvandar.usersandposts.domain.model.ErrorType
import com.metinvandar.usersandposts.domain.model.User
import com.metinvandar.usersandposts.domain.usecase.GetUsersUseCase
import com.metinvandar.usersandposts.presentation.state.UsersUIState
import com.metinvandar.usersandposts.presentation.users.UsersViewModel
import com.metinvandar.usersandposts.utils.MainCoroutineRule
import com.metinvandar.usersandposts.utils.ResourceManager
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsersViewModelTest {

    @get:Rule
    internal val liveDataRule = InstantTaskExecutorRule()

    @get:Rule
    internal val coroutineScopeRule = MainCoroutineRule()

    @MockK
    private lateinit var getUsersUseCase: GetUsersUseCase

    @MockK
    private lateinit var resourceManager: ResourceManager

    private lateinit var viewModel: UsersViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `get users success`() {
        // Given
        coEvery { getUsersUseCase.invoke() } returns flow {
            emit(Resource.Success(users))
        }
        val testObserver = mockk<Observer<UsersUIState>>()
        every { testObserver.onChanged(any()) } returns Unit

        // When
        viewModel = UsersViewModel(getUsersUseCase, resourceManager)
        viewModel.userUIState.observeForever(testObserver)

        // Then
        coVerify { getUsersUseCase.invoke() }

        verify {
            testObserver.onChanged(UsersUIState.Success(users))
        }

        viewModel.userUIState.removeObserver(testObserver)
    }

    @Test
    fun `get users error`() {
        // Given
        val errorMessage = "unknown error"
        coEvery { getUsersUseCase.invoke() } returns flow {
            emit(Resource.Error(ErrorType.UNKNOWN_ERROR))
        }
        every { resourceManager.getString(R.string.unknown_error_message) } returns errorMessage
        val testObserver = mockk<Observer<UsersUIState>>()
        every { testObserver.onChanged(any()) } returns Unit

        // When
        viewModel = UsersViewModel(getUsersUseCase, resourceManager)
        viewModel.userUIState.observeForever(testObserver)

        // Then
        verify {
            testObserver.onChanged(UsersUIState.Failed(errorMessage))
        }

        coVerify { getUsersUseCase.invoke() }

        viewModel.userUIState.removeObserver(testObserver)
    }

    private val users get() = listOf(
        User(id = 1, name = "name", posts = listOf(), image = "imageUrl", postCount = 4)
    )
}