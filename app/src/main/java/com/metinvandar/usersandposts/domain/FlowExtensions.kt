package com.metinvandar.usersandposts.domain

import com.metinvandar.usersandposts.domain.model.ErrorType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.net.UnknownHostException

fun <T> Flow<Resource<T>>.handleErrors(): Flow<Resource<T>> =
    catch { e ->
        val statusCode = e.errorType()
        emit(Resource.Error(statusCode))
    }

fun Throwable.errorType(): ErrorType {
    return when (this) {
        is UnknownHostException -> {
            ErrorType.NETWORK_ERROR
        }
        else -> ErrorType.UNKNOWN_ERROR
    }
}
