package com.cryoggen.studentcontrol.presentation.ui.edit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryoggen.domain.models.*
import com.cryoggen.domain.usecase.*
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState
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
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val deleteStudentUseCase: DeleteStudentUseCase,

) : ViewModel() {

    private val _tasks = MutableLiveData<List<TaskDomain>>()
    val tasks: LiveData<List<TaskDomain>>
        get() = _tasks


    private val _students = MutableLiveData<List<StudentDomain>>()
    val students: LiveData<List<StudentDomain>>
        get() = _students

    fun insertStudentsControl(
        practice: PracticeDomain,
        tasks: List<TaskDomain>,
        students: List<StudentDomain>
    ) {
        val studentsInsert: MutableList<StudentDomain> = mutableListOf()
        val studentsControlDomainList: MutableList<StudentControlDomain> = mutableListOf()

        for (student in students) {
            if (student.name == "") continue
            if (student.id == "") {
                student.id = UUID.randomUUID().toString()
                for (task in tasks) {
                    if (task.id != "") {
                        studentsControlDomainList.add(
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
            studentsInsert.add(StudentDomain(id = student.id, name = student.name))
        }

        for (task in tasks) {
            if (task.name == "") continue
            if (task.id == "") {
                task.id = UUID.randomUUID().toString()
                for (student in studentsInsert) {
                    studentsControlDomainList.add(
                        StudentControlDomain(
                            practiceId = practice.id,
                            taskId = task.id,
                            nameId = student.id,
                            check = false
                        )
                    )
                }
            }

            viewModelScope.launch {
                insertPracticeUseCase.execute(listOf(practice))
                insertTasksUseCase.execute(tasks)
                insertStudentsUseCase.execute(studentsInsert)
                insertStudentControlUseCase.execute(studentsControlDomainList)


            }

        }

    }

        fun getTasks(practiceId: String) {
            viewModelScope.launch {
                _tasks.value = getTasksUseCase.execute(practiceId = practiceId)


            }
        }


        fun getStudents(practiceId: String) {
            viewModelScope.launch {
                _students.value = getStudentsUseCase.execute(practiceId)
            }
        }

    fun deleteTask(taskId:String){
        viewModelScope.launch {
            deleteTaskUseCase.execute(taskId)
        }
    }

    fun deleteStudent(studentId:String){
        viewModelScope.launch {
            deleteStudentUseCase.execute(studentId)
        }
    }

    }