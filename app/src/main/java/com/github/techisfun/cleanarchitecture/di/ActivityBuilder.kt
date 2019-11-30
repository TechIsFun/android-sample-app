package com.github.techisfun.cleanarchitecture.di

import com.github.techisfun.cleanarchitecture.BottomNavigationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindBottomNavigationActivity(): BottomNavigationActivity

}