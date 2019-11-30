package com.github.techisfun.cleanarchitecture.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.techisfun.cleanarchitecture.domain.usecases.GetTaskListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Provider

class TaskListViewModelFactory @Inject constructor(
    private val coroutineDispatcher: Provider<CoroutineDispatcher>,
    private val getExampleModelListUseCase: Provider<GetTaskListUseCase>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            return TaskListViewModel(
                getTaskListUseCase = getExampleModelListUseCase.get()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
