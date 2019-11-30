package com.github.techisfun.cleanarchitecture.domain

interface Mapper<in T, R> {
    fun map(t: T): R
}