package com.metinvandar.usersandposts.domain.usecase

import com.metinvandar.usersandposts.data.repository.UsersRepository
import com.metinvandar.usersandposts.domain.Resource
import com.metinvandar.usersandposts.domain.handleErrors
import com.metinvandar.usersandposts.domain.model.User
import com.metinvandar.usersandposts.domain.model.toUserPost
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetUsersUseCaseImpl @Inject constructor(private val usersRepository: UsersRepository) :
    GetUsersUseCase {

    @FlowPreview
    override suspend fun invoke(): Flow<Resource<List<User>>> {
        return usersRepository.getUsers()
            .zip(usersRepository.getPosts()) { users, posts ->
                val allUsers = mutableListOf<User>()
                users.forEach { userResponseModel ->
                    val userPosts = posts.filter { userResponseModel.userId == it.userId }
                    val user = User(
                        id = userResponseModel.userId,
                        name = userResponseModel.name,
                        posts = userPosts.map { it.toUserPost() },
                        image = userResponseModel.thumbnail,
                        postCount = userPosts.size
                    )
                    allUsers.add(user)
                }
                return@zip Resource.Success(allUsers)
            }.handleErrors()
    }
}
