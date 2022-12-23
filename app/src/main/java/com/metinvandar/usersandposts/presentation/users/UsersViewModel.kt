package com.metinvandar.usersandposts.presentation.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metinvandar.usersandposts.R
import com.metinvandar.usersandposts.domain.Resource
import com.metinvandar.usersandposts.domain.model.ErrorType
import com.metinvandar.usersandposts.domain.usecase.GetUsersUseCase
import com.metinvandar.usersandposts.presentation.state.UsersUIState
import com.metinvandar.usersandposts.utils.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsers: GetUsersUseCase,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val _userUIState = MutableLiveData<UsersUIState>(UsersUIState.Initial)
    val userUIState get() = _userUIState

    init {
        getUsersAndPost()
    }

    fun getUsersAndPost() {
        viewModelScope.launch {
            getUsers().collect { usersResource ->
                when (usersResource) {
                    is Resource.Success -> {
                        _userUIState.value = UsersUIState.Success(usersResource.data)
                    }
                    is Resource.Error -> {
                        val errorMessage = when (usersResource.error) {
                            ErrorType.NETWORK_ERROR -> {
                                resourceManager.getString(R.string.connection_error_message)
                            }
                            ErrorType.UNKNOWN_ERROR -> {
                                resourceManager.getString(R.string.unknown_error_message)
                            }
                        }
                        _userUIState.value = UsersUIState.Failed(errorMessage)
                    }
                    else -> {}
                }
            }
        }
    }
}
