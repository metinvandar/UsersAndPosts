package com.metinvandar.usersandposts.data.models

import com.google.gson.annotations.SerializedName

data class UserResponseModel(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnailUrl")
    val thumbnail: String
)
