package com.cryoggen.studentcontrol.presentation.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cryoggen.domain.models.CheckedStudentDomain
import com.cryoggen.domain.models.PracticeDomain
import com.cryoggen.domain.models.StudentControlDomain
import com.cryoggen.domain.models.TaskDomain
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.ScreenStudentControlList
import com.cryoggen.studentcontrol.presentation.ui.menu.MenuScreen
import com.cryoggen.studentcontrol.presentation.ui.navbar.Navbar


@Composable
fun ScreenStudentControl(
    screenState: ScreenState,
    viewModel: ScreenStudentControlViewModel,
) {

    var menuOpen by remember { mutableStateOf(false) }

    when (screenState) {
        is ScreenState.Practices -> {
            val practices: List<PracticeDomain> by viewModel.practices.observeAsState(initial = listOf())
            viewModel.getPractices()
            screenState.listPractices = practices

        }

        is ScreenState.Tasks -> {

            val tasks: List<TaskDomain> by viewModel.tasks.observeAsState(initial = listOf())

            viewModel.getTasks(screenState.practiceId)
            screenState.listTasks = tasks
            screenState.navBar.iconRightOnClick = { menuOpen = true }
        }

        is ScreenState.Students -> {
            val checkedStudentDomainList: List<CheckedStudentDomain> by viewModel.students.observeAsState(
                initial = listOf()
            )


            screenState.checkedStudentDomainList = checkedStudentDomainList

            screenState.saveCheckStudent =
                { studentControlDomain: StudentControlDomain ->
                    viewModel.insertStudentsControlDomain(
                        listOf(
                            studentControlDomain
                        )
                    )
                }

            screenState.deleteTaskStudent =
                { taskId: String, studentId: String ->
                    viewModel.deleteTaskStudent(
                        practiceId = screenState.practiceId,
                        taskId = taskId,
                        studentId = studentId
                    )

                }

            if (checkedStudentDomainList.isEmpty()) {
                viewModel.getStudents(
                    practiceId = screenState.practiceId,
                    taskId = screenState.taskId
                )
            }
        }
        else -> {}

    }

Box() {
    Column(modifier = Modifier.padding(0.dp)) {
        Navbar(
            screenState = screenState
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
        ) {
            Column {
                ScreenStudentControlList(
                    screenStatus = screenState
                )

            }

        }
    }

    if (screenState is ScreenState.Practices) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.align(alignment = Alignment.BottomEnd)){
                FloatingActionButton(
                    onClick = screenState.floatingButtonOnClick,
                    backgroundColor = MaterialTheme.colors.surface
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.button_add_new_practice_description)
                    )
                }
            }
        }

    }
}

    if (menuOpen) {
        when (screenState) {
            is ScreenState.Practices -> {

            }
            is ScreenState.Tasks -> {
                screenState.onDeletePracticePressed = {
                    viewModel.deletePractice(screenState.practiceId)
                    screenState.navBar.iconLeftOnClick()
                }
                MenuScreen(screenState = screenState, menuClose = { menuOpen = false })
            }
            is ScreenState.Students -> {
            }
            else -> {}
        }
    }
}






