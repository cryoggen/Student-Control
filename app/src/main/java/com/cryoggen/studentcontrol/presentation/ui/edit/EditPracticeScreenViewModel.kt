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
    private val getCheckedStudentsUseCase: GetCheckedStudentsUseCase,
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
        students: List<CheckedStudentDomain>
    ) {
        val studentsInsert:MutableList<StudentDomain> = mutableListOf()
        val studentsControlDomainList: MutableList<StudentControlDomain> = mutableListOf()
        for (student in students) {
            if (student.nameId == "") {
                student.nameId = UUID.randomUUID().toString()
                for (task in tasks) {
                    studentsControlDomainList.add(
                        StudentControlDomain(
                            practiceId = practice.id,
                            taskId = task.id,
                            nameId = student.nameId,
                            check = false
                        )
                    )
                }
            }
            studentsInsert.add(StudentDomain(id = student.nameId, name = student.name))
        }

        viewModelScope.launch {
            insertPracticeUseCase.execute(listOf(practice))
            insertTasksUseCase.execute(tasks)
            insertStudentsUseCase.execute(studentsInsert)
            insertStudentControlUseCase.execute(studentsControlDomainList)

        }

    }

    fun getTasks(practiceId: String) {
        viewModelScope.launch {
            _tasks.value = getTasksUseCase.execute(practiceId = practiceId)
        }
    }

    fun getStudents(practiceId: String, taskId: String) {
        viewModelScope.launch {
            _students.value = getCheckedStudentsUseCase.execute(practiceId = practiceId, taskId = taskId)
        }
    }

    fun getPracticeData(practiceId: String) {
        viewModelScope.launch {
            val tasks = getTasksUseCase.execute(practiceId = practiceId)
            val students: MutableList<CheckedStudentDomain> = mutableListOf()
            for (task in tasks) {
                students.addAll(getCheckedStudentsUseCase.execute(practiceId = practiceId, taskId = task.id))
            }
            _tasks.value = tasks
            _students.value = students
        }
    }

}