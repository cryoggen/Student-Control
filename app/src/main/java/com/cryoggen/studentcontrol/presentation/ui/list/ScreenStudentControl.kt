package com.cryoggen.studentcontrol.presentation.ui.list

import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import com.cryoggen.studentcontrol.presentation.ui.menu.MenuType
import com.cryoggen.studentcontrol.presentation.ui.navbar.Navbar
import java.lang.Exception

enum class SortStudents {
    CHECKED, UNCHECKED, ALL, NONE
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ScreenStudentControl(
    screenState: ScreenState,
    viewModel: ScreenStudentControlViewModel,
) {
    var sortStudents by remember { mutableStateOf(SortStudents.ALL) }
    var menuOpen by remember { mutableStateOf(false) }

    val practices: List<PracticeDomain> by viewModel.practices.observeAsState(initial = listOf())
    val tasks: List<TaskDomain> by viewModel.tasks.observeAsState(initial = listOf())
    val checkedStudentDomainList: List<CheckedStudentDomain> by viewModel.checkedStudentDomainList.observeAsState(
        initial = mutableListOf()
    )

    when (screenState) {

        is ScreenState.Practices -> {
            viewModel.getPractices()
            screenState.listPractices = practices


        }

        is ScreenState.Tasks -> {
            viewModel.getTasks(screenState.practiceId)
            screenState.listTasks = tasks
            screenState.navBar.iconRightOnClick = { menuOpen = true }
        }

        is ScreenState.Students -> {

            when (sortStudents) {
                SortStudents.NONE -> {
                }
                SortStudents.ALL -> {
                    viewModel.clearListStudentsChecked()
                    viewModel.getStudents(
                        practiceId = screenState.practiceId,
                        taskId = screenState.taskId
                    )
                    sortStudents = SortStudents.NONE
                }

                SortStudents.CHECKED -> {
                    viewModel.clearListStudentsChecked()
                    viewModel.getSortStudentsChecked(
                        practiceId = screenState.practiceId,
                        taskId = screenState.taskId
                    )
                    sortStudents = SortStudents.NONE
                }

                SortStudents.UNCHECKED -> {
                    viewModel.clearListStudentsChecked()
                    viewModel.getSortStudentsUnchecked(
                        practiceId = screenState.practiceId,
                        taskId = screenState.taskId
                    )
                    sortStudents = SortStudents.NONE
                }
                else -> {}
            }

            screenState.navBar.iconRightOnClick = { menuOpen = true }

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
                Column(modifier = Modifier.align(alignment = Alignment.BottomEnd)) {
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
            is ScreenState.Students -> {
                MenuScreen(
                    menuType = MenuType.STUDENTS,
                    menuClose = { menuOpen = false },
                    onClickIconLeft = {
                        sortStudents = SortStudents.CHECKED
                        menuOpen = false
                    },
                    onClickIconCenter = {
                        sortStudents = SortStudents.UNCHECKED
                        menuOpen = false
                    },
                    onClickIconRight = {
                        sortStudents = SortStudents.ALL
                        menuOpen = false
                    }
                )
            }
            is ScreenState.Tasks -> {
                MenuScreen(
                    menuType = MenuType.TASKS, menuClose = { menuOpen = false },
                    onClickIconLeft = {
                        viewModel.deletePractice(screenState.practiceId)
                        screenState.navBar.iconLeftOnClick()
                    },
                    onClickIconRight = screenState.onEditPracticePressed
                )
            }

            else -> {}
        }
    }


}







