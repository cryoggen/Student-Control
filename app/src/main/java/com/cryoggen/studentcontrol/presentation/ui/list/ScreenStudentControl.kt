package com.cryoggen.studentcontrol.presentation.ui.list

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
import com.cryoggen.domain.models.StudentDomain
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.ScreenStudentControlList
import com.cryoggen.studentcontrol.presentation.ui.menu.MenuScreen
import com.cryoggen.studentcontrol.presentation.ui.navbar.Navbar


@Composable
fun ScreenStudentControl(
    screenState: ScreenState,
    viewModel: ScreenStudentControlViewModel,
) {

    val menuOpen by remember { mutableStateOf(false) }

    when (screenState) {
        is ScreenState.Practices -> {
            val practices: List<String> by viewModel.practices.observeAsState(initial = listOf())
            viewModel.getPractices()
            screenState.listPractices = practices
        }

        is ScreenState.Tasks -> {
            val tasks: List<String> by viewModel.tasks.observeAsState(initial = listOf())
            viewModel.getTasks(practice = screenState.practice)
            screenState.listTasks = tasks
        }

        is ScreenState.Students -> {
            val students: List<StudentDomain> by viewModel.students.observeAsState(
                initial = listOf()
            )

            viewModel.getStudents(practice = screenState.practice, task = screenState.task)
            screenState.listStudents = students

            screenState.saveCheckStudent =
                { student: StudentDomain -> viewModel.insertStudents(listOf(student)) }

            screenState.deleteStudent =
                { student: StudentDomain ->
                    viewModel.deleteStudents(listOf(student))
                    viewModel.getStudents(practice = screenState.practice, task = screenState.task)
                }

        }
        is ScreenState.Edit -> {}
    }


    Column(modifier = Modifier.padding(0.dp)) {
        Navbar(
            screenState = screenState
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
        ) {
            Column() {
                ScreenStudentControlList(
                    screenStatus = screenState
                )
            }

            if (screenState is ScreenState.Practices) {
                Box(
                    contentAlignment = Alignment.BottomEnd, modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                        .fillMaxSize()
                ) {
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
        MenuScreen()
    }
}






