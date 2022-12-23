package com.metinvandar.usersandposts.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val posts: List<UserPost>,
    val image: String,
    val postCount: Int
): Parcelable
