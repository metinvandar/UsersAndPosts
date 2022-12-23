package com.metinvandar.usersandposts.presentation.state

import com.metinvandar.usersandposts.domain.model.User

sealed class UsersUIState(
    open val loading: Boolean,
    open val users: List<User>,
    open val errorMessage: String?
) {

    object Initial : UsersUIState(loading = true, users = emptyList(), errorMessage = null)

    data class Success(override val users: List<User>) :
        UsersUIState(users = users, loading = false, errorMessage = null)

    data class Failed(override val errorMessage: String) :
        UsersUIState(loading = false, users = emptyList(), errorMessage = null)
}
