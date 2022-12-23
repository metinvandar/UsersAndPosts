package com.metinvandar.usersandposts.domain.usecase

import com.metinvandar.usersandposts.domain.Resource
import com.metinvandar.usersandposts.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {

    suspend operator fun invoke(): Flow<Resource<List<User>>>

}
