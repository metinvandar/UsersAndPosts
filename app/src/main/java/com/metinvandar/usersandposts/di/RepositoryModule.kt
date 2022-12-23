package com.metinvandar.usersandposts.di

import com.metinvandar.usersandposts.data.api.UsersApi
import com.metinvandar.usersandposts.data.repository.UsersRepository
import com.metinvandar.usersandposts.data.repository.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepository(usersApi: UsersApi): UsersRepository = UsersRepositoryImpl(usersApi)
}
