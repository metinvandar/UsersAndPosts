package com.metinvandar.usersandposts.data.api

import com.metinvandar.usersandposts.data.models.PostResponseModel
import com.metinvandar.usersandposts.data.models.UserResponseModel
import retrofit2.http.GET

interface UsersApi {

    @GET("users")
    suspend fun getUsers(): List<UserResponseModel>

    @GET("posts")
    suspend fun getPosts(): List<PostResponseModel>
}
