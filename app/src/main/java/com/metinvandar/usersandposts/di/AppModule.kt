package com.metinvandar.usersandposts.di

import android.content.Context
import android.content.res.Configuration
import com.metinvandar.usersandposts.utils.ResourceManager
import com.metinvandar.usersandposts.utils.ResourceManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideResourceManager(@ApplicationContext context: Context): ResourceManager {
        val config = Configuration(context.resources.configuration)
        config.setLocale(Locale.getDefault())
        val configContext = context.createConfigurationContext(config)
        return ResourceManagerImpl(configContext)
    }
}
