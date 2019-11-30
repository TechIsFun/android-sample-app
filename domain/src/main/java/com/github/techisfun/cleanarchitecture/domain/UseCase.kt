package com.github.techisfun.cleanarchitecture.domain

/**
 * @author Andrea Maglie
 */
interface UseCase<in P, R> {
    suspend fun execute(parameters: P): R
}