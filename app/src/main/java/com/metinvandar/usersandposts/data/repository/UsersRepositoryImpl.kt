package com.metinvandar.usersandposts.data.repository

import com.metinvandar.usersandposts.data.api.UsersApi
import com.metinvandar.usersandposts.data.models.PostResponseModel
import com.metinvandar.usersandposts.data.models.UserResponseModel
import com.metinvandar.usersandposts.domain.Resource
import com.metinvandar.usersandposts.domain.handleErrors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UsersRepositoryImpl(private val usersApi: UsersApi) : UsersRepository {

    override suspend fun getUsers(): Flow<List<UserResponseModel>> = flow {
        emit(usersApi.getUsers())
    }.flowOn(Dispatchers.IO)

    override suspend fun getPosts(): Flow<List<PostResponseModel>> = flow {
        emit(usersApi.getPosts())
    }.flowOn(Dispatchers.IO)
}
