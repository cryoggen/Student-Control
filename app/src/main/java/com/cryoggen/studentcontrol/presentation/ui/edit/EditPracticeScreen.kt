package com.cryoggen.studentcontrol.presentation.ui.edit

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cryoggen.domain.models.*
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState
import com.cryoggen.studentcontrol.presentation.ui.navbar.Navbar
import java.util.*

@Composable
fun EditPracticeScreen(
    viewModel: EditPracticeScreenViewModel,
    screenState: ScreenState
) {
    var practiceId = UUID.randomUUID().toString()
    var practiceName = ""
    var onBackPressed = {}
    var savePracticeButtonClicked by remember {
        mutableStateOf(false)
    }

    when (screenState) {
        is ScreenState.NewPractice -> {
            onBackPressed = { screenState.navBar.iconLeftOnClick() }
            screenState.navBar.iconRightOnClick = {
                savePracticeButtonClicked = true
            }
        }
        is ScreenState.EditPractice -> {
            onBackPressed = { screenState.navBar.iconLeftOnClick() }
            screenState.navBar.iconRightOnClick = {
                savePracticeButtonClicked = true
            }
            practiceId = screenState.practiceId
            practiceName = screenState.practiceName
        }
        else -> {}
    }


    var practice by remember {
        mutableStateOf(
            PracticeDomain(
                id = practiceId,
                name = practiceName
            )
        )
    }


    val tasks = remember { mutableStateListOf(TaskDomain(id = "", name = "")) }

    val students = remember {
        mutableStateListOf(
            StudentDomain(
                id = "",
                name = "",
            )
        )
    }

    if ((screenState is ScreenState.EditPractice) && (tasks.size == 1) && (tasks[0].name == "")) {

        val tasksDomain: List<TaskDomain> by viewModel.tasks.observeAsState(
            initial = listOf(

            )
        )
        val studentsDomain: List<StudentDomain> by viewModel.students.observeAsState(
            initial = listOf(

            )
        )

        if (!tasksDomain.isEmpty()) {
            tasks.clear()
            for (task in tasksDomain) {
                tasks.add(task)
            }
        }

        if (!studentsDomain.isEmpty()) {
            students.clear()
            for (student in studentsDomain) {
                students.add(student)
            }
        }

        viewModel.getStudents(practiceId = practiceId)
        viewModel.getTasks(practiceId = practiceId)
    }

    when {

        practiceVerification(practice) && savePracticeButtonClicked -> {
            SnackBar(stringResource(id = R.string.practice_not_introduced), stopSnackBar = {
                savePracticeButtonClicked = false
            })
        }

        tasksVerification(tasks) && savePracticeButtonClicked -> {
            SnackBar(stringResource(id = R.string.tasks_not_introduced), stopSnackBar = {
                savePracticeButtonClicked = false
            })

        }
        studentsVerification(students) && savePracticeButtonClicked -> {
            SnackBar(stringResource(id = R.string.students_not_introduced), stopSnackBar = {
                savePracticeButtonClicked = false
            })

        }

        savePracticeButtonClicked -> {

            viewModel.insertStudentsControl(
                practice = practice,
                tasks = tasks,
                students = students
            )
            onBackPressed()
            savePracticeButtonClicked = false
        }

    }

    Column() {
        Navbar(
            screenState = screenState
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())

        ) {

            NewPracticeTextTitleEdit(text = stringResource(id = R.string.practice_name))

            NewPracticeEditField(
                value = practice.name,
                onValueChange = {
                    practice = practice.copy(name = it)
                    savePracticeButtonClicked = false
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                placeholder = R.string.new_practice_name_value,
                editFieldStatus = EditFieldStatus.PRACTICES,
            )

            Spacer(Modifier.height(32.dp))

            NewPracticeTextTitleEdit(text = stringResource(id = R.string.tasks))

            for (i in 0 until tasks.size) {
                NewPracticeEditField(
                    value = tasks[i].name,
                    onValueChange = {
                        tasks[i] = tasks[i].copy(name = it)
                        savePracticeButtonClicked = false
                    },
                    onDeleteEditField = {
                        if (tasks[i].id != "") {
                            viewModel.deleteTask(tasks[i].id)
                        }
                        tasks.removeAt(i)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    placeholder = R.string.new_task_name_value,
                    editFieldStatus = EditFieldStatus.TASKS
                )
            }

            NewPracticeScreenButton(
                text = stringResource(id = R.string.new_task_name_text_button),
                addNewEditField = { tasks.add(TaskDomain(id = "", name = "")) }
            )

            Spacer(Modifier.height(32.dp))

            NewPracticeTextTitleEdit(text = stringResource(id = R.string.students))
            for (i in 0 until students.size) {
                NewPracticeEditField(
                    value = students[i].name,
                    onValueChange = {
                        students[i] = students[i].copy(name = it)
                        savePracticeButtonClicked = false
                    },
                    onDeleteEditField = {
                        if (students[i].id != "") {
                            viewModel.deleteStudent(students[i].id)
                        }
                        students.removeAt(i) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    placeholder = R.string.new_student_name_value,
                    editFieldStatus = EditFieldStatus.STUDENTS
                )
            }
            NewPracticeScreenButton(
                text = stringResource(id = R.string.new_student_text_button),
                addNewEditField = {
                    students.add(
                        StudentDomain(
                            id = "",
                            name = "",
                        )
                    )
                }
            )
        }

    }


}

fun studentsVerification(students: List<StudentDomain>): Boolean {
    for (student in students) {
        if (student.name != "") {
            return false
        }
    }

    return true
}


fun tasksVerification(tasks: List<TaskDomain>): Boolean {
    for (task in tasks) {
        if (task.name != "") {
            return false
        }
    }

    return true
}

fun practiceVerification(practice: PracticeDomain): Boolean {
    return practice.name == ""
}


