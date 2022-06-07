package com.cryoggen.studentcontrol.presentation.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryoggen.domain.models.StudentDomain
import com.cryoggen.domain.usecase.GetPracticesUseCase
import com.cryoggen.domain.usecase.GetStudentsUseCase
import com.cryoggen.domain.usecase.GetTasksUseCase
import com.cryoggen.domain.usecase.InsertStudentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val getPracticesUseCase: GetPracticesUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {


    private val _students = MutableLiveData<List<StudentDomain>>()
    val students: LiveData<List<StudentDomain>>
    get() = _students

    private val _tasks = MutableLiveData<List<String>>()
    val tasks: LiveData<List<String>>
    get() = _tasks

    private val _practices = MutableLiveData<List<String>>()
    val practices: LiveData<List<String>>
    get() = _practices

    fun getStudents(practice: String, task: String) {
        viewModelScope.launch {
            _students.value = getStudentsUseCase.execute(practice = practice, task = task)
        }
    }

    fun getTasks(practice: String) {
        viewModelScope.launch {
            _tasks.value = getTasksUseCase.execute(practice = practice)
        }
    }

     fun getPractices() {
        viewModelScope.launch {
            _practices.value = getPracticesUseCase.execute()
        }
    }

}


