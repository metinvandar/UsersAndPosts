package com.metinvandar.usersandposts.data.models

import com.google.gson.annotations.SerializedName

data class PostResponseModel(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val postId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)
