package com.github.techisfun.cleanarchitecture.domain.models

import org.threeten.bp.LocalDateTime

data class TaskModel(
    val id: Int,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val status: TaskStatus
)

enum class TaskStatus {
    OPEN,
    IN_PROGRESS,
    BLOCKED,
    IN_REVIEW,
    CLOSED
}