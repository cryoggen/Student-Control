package com.cryoggen.studentcontrol.presentation.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryoggen.domain.models.*
import com.cryoggen.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenStudentControlViewModel @Inject constructor(
    private val getPracticesUseCase: GetPracticesUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
    private val getTasksUseCase: GetTasksUseCase,
    private val insertStudentControlUseCase: InsertStudentControlUseCase,
    private val deleteStudentUseCase: DeleteStudentUseCase,
) : ViewModel() {

    private val _students = MutableLiveData<List<CheckedStudentDomain>>()
    val students: LiveData<List<CheckedStudentDomain>>
    get() = _students

    private val _tasks = MutableLiveData<List<TaskDomain>>()
    val tasks: LiveData<List<TaskDomain>>
    get() = _tasks

    private val _practices = MutableLiveData<List<PracticeDomain>>()
    val practices: LiveData<List<PracticeDomain>>
    get() = _practices

    fun getStudents(practiceId: String, taskId: String) {
        viewModelScope.launch {
            _students.value = getStudentsUseCase.execute(practiceId = practiceId, taskId = taskId)
        }
    }

    fun getTasks(practiceId: String) {
        viewModelScope.launch {
            _tasks.value = getTasksUseCase.execute(practiceId = practiceId)
        }
    }

     fun getPractices() {
        viewModelScope.launch {
            _practices.value = getPracticesUseCase.execute()
        }
    }

    fun insertStudentsControlDomain(studentControlDomainList :List<StudentControlDomain>) {
        Log.d("11111", studentControlDomainList.toString())
        viewModelScope.launch {
            insertStudentControlUseCase.execute(studentControlDomainList = studentControlDomainList)
        }
    }

    fun deleteStudent(studentId:String) {
        viewModelScope.launch {
            deleteStudentUseCase.execute(studentId)
        }
    }


}


