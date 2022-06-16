package com.cryoggen.studentcontrol.presentation.ui.edit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryoggen.domain.models.*
import com.cryoggen.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditPracticeScreenViewModel @Inject constructor(
    private val insertStudentsUseCase: InsertStudentsUseCase,
    private val insertTasksUseCase: InsertTasksUseCase,
    private val insertPracticeUseCase: InsertPracticeUseCase,
    private val insertStudentControlUseCase: InsertStudentControlUseCase,
    private val getTasksUseCase: GetTasksUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
) : ViewModel() {

    private val _tasks = MutableLiveData<List<TaskDomain>>()
    val tasks: LiveData<List<TaskDomain>>
        get() = _tasks

    private val _students = MutableLiveData<List<CheckedStudentDomain>>()
    val students: LiveData<List<CheckedStudentDomain>>
        get() = _students

    fun insertStudentsControl(
        practice: PracticeDomain,
        tasks: List<TaskDomain>,
        students: List<StudentDomain>
    ) {
        val newStudentsControlDomainList: MutableList<StudentControlDomain> = mutableListOf()
        for (student in students) {
            if (student.id == "") {
                student.id = UUID.randomUUID().toString()
                for (task in tasks) {
                    newStudentsControlDomainList.add(
                        StudentControlDomain(
                            practiceId = practice.id,
                            taskId = task.id,
                            nameId = student.id,
                            check = false
                        )
                    )
                }
            }
        }

        viewModelScope.launch {
            insertPracticeUseCase.execute(listOf(practice))
            insertTasksUseCase.execute(tasks)
            insertStudentsUseCase.execute(students)
            insertStudentControlUseCase.execute(newStudentsControlDomainList)
        }

    }

    fun getTasks(practiceId: String) {
        viewModelScope.launch {
            _tasks.value = getTasksUseCase.execute(practiceId = practiceId)
        }
    }

    fun getStudents(practiceId: String, taskId: String) {
        viewModelScope.launch {
            _students.value = getStudentsUseCase.execute(practiceId = practiceId, taskId = taskId)
        }
    }
}