package com.github.techisfun.cleanarchitecture.domain

import com.github.techisfun.cleanarchitecture.domain.models.TaskModel

interface Repository {

    suspend fun getExampleModelList(): List<TaskModel>
}