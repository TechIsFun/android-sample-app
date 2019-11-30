package com.github.techisfun.cleanarchitecture.di

import com.github.techisfun.cleanarchitecture.tasklist.TaskListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Andrea Maglie
 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeExampleListFragment(): TaskListFragment

}
