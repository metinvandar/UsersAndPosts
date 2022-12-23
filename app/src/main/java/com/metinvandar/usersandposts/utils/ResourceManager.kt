package com.metinvandar.usersandposts.utils

interface ResourceManager {
    fun getString(resId: Int, vararg formatArgs: Any): String
    fun getString(resId: Int): String
}
