package com.github.techisfun.cleanarchitecture

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.github.techisfun.cleanarchitecture.BuildConfig
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import com.github.techisfun.cleanarchitecture.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Andrea Maglie
 */
class CustomApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AndroidThreeTen.init(this)

    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
