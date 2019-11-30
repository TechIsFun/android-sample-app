package com.github.techisfun.cleanarchitecture.tasklist

import com.github.techisfun.cleanarchitecture.domain.Mapper
import com.github.techisfun.cleanarchitecture.domain.models.TaskModel
import com.github.techisfun.cleanarchitecture.domain.models.TaskStatus
import org.threeten.bp.format.DateTimeFormatter

class TaskUiModelMapper: Mapper<TaskModel, TaskUiModel> {

    private var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun map(t: TaskModel): TaskUiModel {
        return TaskUiModel(
            title = t.title,
            description = t.description,
            date = formatter.format(t.date),
            status = t.status.format()
        )
    }
}

private fun TaskStatus.format(): String {
    return this.toString()
}
