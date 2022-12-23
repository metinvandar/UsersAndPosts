package com.metinvandar.usersandposts.domain.model

import android.os.Parcelable
import com.metinvandar.usersandposts.data.models.PostResponseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserPost(
    val postId: Int,
    val title: String,
    val body: String
): Parcelable

fun PostResponseModel.toUserPost(): UserPost {
    return UserPost(postId, title, body)
}
