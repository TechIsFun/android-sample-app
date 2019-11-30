package com.github.techisfun.cleanarchitecture.tasklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.techisfun.cleanarchitecture.domain.SingleLiveEvent
import com.github.techisfun.cleanarchitecture.domain.Result
import com.github.techisfun.cleanarchitecture.domain.usecases.GetTaskListUseCase
import kotlinx.coroutines.launch

class TaskListViewModel(private val getTaskListUseCase: GetTaskListUseCase): ViewModel() {

    private val _uiModelLiveData = MediatorLiveData<List<TaskUiModel>>()
    val uiModelLiveData: LiveData<List<TaskUiModel>>
        get() = _uiModelLiveData

    private val _loadingLiveData = MediatorLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    private val _errorLiveData = SingleLiveEvent<Exception>()
    val errorLiveData: LiveData<Exception>
        get() = _errorLiveData

    private val _navigateDetails = SingleLiveEvent<Pair<Int, Boolean>>()
    val navigateDetails: LiveData<Pair<Int, Boolean>>
        get() = _navigateDetails

    private val uiModelMapper = TaskUiModelMapper()

    init {
        val observable = getTaskListUseCase.observe()

        _loadingLiveData.removeSource(observable)
        _loadingLiveData.addSource(observable) {
            _loadingLiveData.postValue(it == Result.Loading)
        }

        _errorLiveData.removeSource(observable)
        _errorLiveData.addSource(observable) {
            if (it is Result.Error) _errorLiveData.postValue(it.exception)
        }

        _uiModelLiveData.addSource(observable) { result ->
            if (result is Result.Success) {
                _uiModelLiveData.postValue(
                    result.data.map { uiModelMapper.map(it) }
                )
            }
        }

        viewModelScope.launch { getTaskListUseCase.execute(Unit) }
    }
}
