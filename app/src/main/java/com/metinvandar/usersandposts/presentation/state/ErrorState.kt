package com.metinvandar.usersandposts.presentation.state

import com.metinvandar.usersandposts.domain.model.ErrorType

interface ErrorState {
    val errorType: ErrorType
}
