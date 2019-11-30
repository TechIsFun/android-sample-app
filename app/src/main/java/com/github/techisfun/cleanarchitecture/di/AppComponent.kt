package com.github.techisfun.cleanarchitecture.di

import android.app.Application
import com.github.techisfun.cleanarchitecture.CustomApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * @author Andrea Maglie
 */
@Singleton
@Component(
    modules = [AppModule::class,
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        FragmentModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: CustomApplication)
}