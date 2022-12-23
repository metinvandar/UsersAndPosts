package com.metinvandar.usersandposts

import com.metinvandar.usersandposts.data.models.PostResponseModel
import com.metinvandar.usersandposts.data.models.UserResponseModel
import com.metinvandar.usersandposts.data.repository.UsersRepository
import com.metinvandar.usersandposts.domain.Resource
import com.metinvandar.usersandposts.domain.usecase.GetUsersUseCase
import com.metinvandar.usersandposts.domain.usecase.GetUsersUseCaseImpl
import com.metinvandar.usersandposts.utils.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetUsersUseCaseTest {

    @get:Rule
    internal val coroutineScopeRule = MainCoroutineRule()

    @MockK
    private lateinit var usersRepository: UsersRepository

    private lateinit var getUsersUseCase: GetUsersUseCase

    val userIdToTest = 2

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getUsersUseCase = GetUsersUseCaseImpl(usersRepository)
    }

    @Test
    fun `get users and post success`() = runTest {
        // Given
        coEvery { usersRepository.getUsers() } returns flow {
            emit(userResponse)
        }

        coEvery { usersRepository.getPosts() } returns flow {
            emit(postsResponse)
        }

        // Then
        getUsersUseCase.invoke().collect { resource ->
            assert(resource is Resource.Success)
            val users = (resource as Resource.Success).data
            assert(users.size == userResponse.size)

            val resultPosts = postsResponse.filter { it.userId == userIdToTest }
            val selectedUser = users.find { it.id == userIdToTest }
            assert(selectedUser!!.postCount == resultPosts.size)
        }

        coVerifySequence {
            usersRepository.getUsers()
            usersRepository.getPosts()
        }
    }

    private val userResponse
        get() = listOf(
            UserResponseModel(
                userId = 1,
                name = "name1",
                thumbnail = "thumbnail1",
                albumId = 1
            ),
            UserResponseModel(
                userId = 4,
                name = "name4",
                thumbnail = "thumbnail4",
                albumId = 4
            ),
            UserResponseModel(
                userId = userIdToTest,
                name = "name2",
                thumbnail = "thumbnail2",
                albumId = 2
            ),
            UserResponseModel(
                userId = 3,
                name = "name3",
                thumbnail = "thumbnail3",
                albumId = 3
            )
        )

    private val postsResponse
        get() = listOf(
            PostResponseModel(userId = 4, postId = 15, title = "title15", body = "body15"),
            PostResponseModel(userId = 1, postId = 16, title = "title16", body = "body16"),
            PostResponseModel(userId = 3, postId = 19, title = "title19", body = "body19"),
            PostResponseModel(userId = userIdToTest, postId = 11, title = "title10", body = "body2"),
            PostResponseModel(userId = 7, postId = 21, title = "title7", body = "body7"),
            PostResponseModel(userId = 5, postId = 25, title = "title5", body = "body5"),
            PostResponseModel(userId = userIdToTest, postId = 34, title = "title8", body = "body8"),
            PostResponseModel(userId = userIdToTest, postId = 38, title = "title6", body = "body6"),
        )
}
