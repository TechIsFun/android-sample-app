package com.github.techisfun.cleanarchitecture.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.github.techisfun.cleanarchitecture.data.RepositoryMock
import com.github.techisfun.cleanarchitecture.domain.Repository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.util.*
import javax.inject.Singleton

/**
 * @author Andrea Maglie
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(application: Application): Context = application

    @Provides
    fun provideLocale(): Locale = Locale.getDefault()

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    fun provideRepository(): Repository = RepositoryMock()

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

}

