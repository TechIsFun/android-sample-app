package com.github.techisfun.cleanarchitecture.domain

import androidx.lifecycle.MediatorLiveData

abstract class MediatorUseCase<in P, R> {
    protected val result = MediatorLiveData<Result<R>>()

    // Make this as open so that mock instances can mock this method
    open fun observe(): MediatorLiveData<Result<R>> {
        return result
    }

    abstract suspend fun execute(parameters: P)
}
