package com.github.techisfun.cleanarchitecture.data

import com.github.techisfun.cleanarchitecture.domain.Repository
import com.github.techisfun.cleanarchitecture.domain.models.TaskModel
import com.github.techisfun.cleanarchitecture.domain.models.TaskStatus
import kotlinx.coroutines.delay
import org.threeten.bp.LocalDateTime

class RepositoryMock : Repository {

    override suspend fun getExampleModelList(): List<TaskModel> {
        delay(1000) // simulate delay
        return listOf(
            TaskModel(1, "Task 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit", LocalDateTime.now(), TaskStatus.OPEN),
            TaskModel(2, "Task 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit", LocalDateTime.now(), TaskStatus.OPEN),
            TaskModel(3, "Task 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit", LocalDateTime.now(), TaskStatus.OPEN),
            TaskModel(4, "Task 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit", LocalDateTime.now(), TaskStatus.OPEN),
            TaskModel(5, "Task 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit", LocalDateTime.now(), TaskStatus.OPEN),
            TaskModel(6, "Task 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit", LocalDateTime.now(), TaskStatus.OPEN),
            TaskModel(7, "Task 7", "Lorem ipsum dolor sit amet, consectetur adipiscing elit", LocalDateTime.now(), TaskStatus.OPEN),
            TaskModel(8, "Task 8", "Lorem ipsum dolor sit amet, consectetur adipiscing elit", LocalDateTime.now(), TaskStatus.OPEN)
        )
    }
}