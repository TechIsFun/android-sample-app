package com.github.techisfun.cleanarchitecture.domain.usecases

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.github.techisfun.cleanarchitecture.domain.MediatorUseCase
import com.github.techisfun.cleanarchitecture.domain.Repository
import com.github.techisfun.cleanarchitecture.domain.Result
import com.github.techisfun.cleanarchitecture.domain.models.TaskModel
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class GetTaskListUseCase2 @Inject constructor(private val repository: Repository) {

    private val result = MutableLiveData<Result<List<TaskModel>>>()

    suspend fun execute(parameters: Unit) {
        result.postValue(Result.Loading)

        try {
            val model = repository.getExampleModelList()
            result.postValue(Result.Success(model))
        } catch (e: Exception) {
            Timber.w(e)
            result.postValue(Result.Error(e))
        }
    }

    fun observe(): MutableLiveData<Result<List<TaskModel>>> {
        return result
    }
}