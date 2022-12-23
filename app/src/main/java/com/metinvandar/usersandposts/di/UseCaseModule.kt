package com.metinvandar.usersandposts.di

import com.metinvandar.usersandposts.data.repository.UsersRepository
import com.metinvandar.usersandposts.domain.usecase.GetUsersUseCase
import com.metinvandar.usersandposts.domain.usecase.GetUsersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetUsersUseCase(repository: UsersRepository): GetUsersUseCase {
        return GetUsersUseCaseImpl(repository)
    }
}
