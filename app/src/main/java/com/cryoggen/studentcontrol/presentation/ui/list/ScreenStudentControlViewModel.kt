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
    private val getCheckedStudentsUseCase: GetCheckedStudentsUseCase,
    private val getTasksUseCase: GetTasksUseCase,
    private val getStudentsControlListUseCase: GetStudentsControlListUseCase,
    private val insertStudentControlUseCase: InsertStudentControlUseCase,
    private val deleteStudentUseCase: DeleteStudentUseCase,
    private val deletePracticeUseCase: DeletePracticeUseCase,
    private val deleteTaskStudentUseCase: DeleteTaskStudentUseCase

) : ViewModel() {

    private val _checkedStudentDomainList = MutableLiveData<List<CheckedStudentDomain>>()
    val checkedStudentDomainList: LiveData<List<CheckedStudentDomain>>
        get() = _checkedStudentDomainList

    private val _studentsControlList = MutableLiveData<List<StudentControlDomain>>()
    val studentsControlList: LiveData<List<StudentControlDomain>>
        get() = _studentsControlList

    private val _tasks = MutableLiveData<List<TaskDomain>>()
    val tasks: LiveData<List<TaskDomain>>
        get() = _tasks

    private val _practices = MutableLiveData<List<PracticeDomain>>()
    val practices: LiveData<List<PracticeDomain>>
        get() = _practices

    fun getStudents(practiceId: String, taskId: String) {
        viewModelScope.launch {
            _checkedStudentDomainList.value = getCheckedStudentsUseCase.execute(practiceId = practiceId, taskId = taskId)
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

    fun insertStudentsControlDomain(studentControlDomainList: List<StudentControlDomain>) {
        viewModelScope.launch {
            insertStudentControlUseCase.execute(studentControlDomainList = studentControlDomainList)
        }
    }

    fun deleteStudent(studentId: String) {
        viewModelScope.launch {
            deleteStudentUseCase.execute(studentId)
        }
    }

    fun deletePractice(practiceId: String) {
        viewModelScope.launch {
            deletePracticeUseCase.execute(practiceId)
        }
    }

    fun deleteTaskStudent(practiceId: String, taskId: String, studentId: String, ) {
        viewModelScope.launch {
            deleteTaskStudentUseCase.execute(taskId = taskId, studentId = studentId)
        }
    }

    fun getStudentsControlListUseCase(practiceId: String, taskId: String) {
        viewModelScope.launch {
            _studentsControlList.value =
                getStudentsControlListUseCase.execute(practiceId = practiceId, taskId = taskId)
        }
    }

    fun getSortStudentsChecked(practiceId: String, taskId: String) {
        viewModelScope.launch {
            _checkedStudentDomainList.value = getCheckedStudentsUseCase.execute(practiceId = practiceId, taskId = taskId).filter { it.check == true }
        }
    }

    fun getSortStudentsUnchecked(practiceId: String, taskId: String) {
        viewModelScope.launch {
            _checkedStudentDomainList.value = getCheckedStudentsUseCase.execute(practiceId = practiceId, taskId = taskId).filter { it.check == false }

        }
    }

    fun clearListStudentsChecked() {
        viewModelScope.launch {
            _checkedStudentDomainList.value = listOf()
        }
    }


}


