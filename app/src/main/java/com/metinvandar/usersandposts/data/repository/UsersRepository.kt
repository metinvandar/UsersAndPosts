package com.metinvandar.usersandposts.data.repository

import com.metinvandar.usersandposts.data.models.PostResponseModel
import com.metinvandar.usersandposts.data.models.UserResponseModel
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun getUsers(): Flow<List<UserResponseModel>>

    suspend fun getPosts(): Flow<List<PostResponseModel>>
}
