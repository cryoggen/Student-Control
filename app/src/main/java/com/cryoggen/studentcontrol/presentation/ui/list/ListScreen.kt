package com.cryoggen.studentcontrol.presentation.ui.list

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cryoggen.domain.models.StudentDomain
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.presentation.ui.navhost.ListScreenStatus
import com.cryoggen.studentcontrol.ListItems
import com.cryoggen.studentcontrol.presentation.ui.navbar.Navbar


@Composable
fun ListScreen(
    onClickItemList: (String, String) -> Unit,
    onClickFloatingButton: () -> Unit = {},
    listStatus: ListScreenStatus,
    viewModel: ListScreenViewModel,
    icoLeftOnClick: () -> Unit,
    iconLeft: ImageVector,
    text: String,
    iconRight: ImageVector,
    icoRightOnClick: () -> Unit
) {

    val practices: List<String> by viewModel.practices.observeAsState(initial = listOf(""))
    val tasks: List<String> by viewModel.tasks.observeAsState(initial = listOf(""))
    val students: List<StudentDomain> by viewModel.students.observeAsState(
        initial = listOf(
            StudentDomain(name ="", practice = "", task = "")
        )
    )

    when (listStatus) {
        is ListScreenStatus.Practices -> {
            viewModel.getPractices()
            listStatus.listPractices = practices
        }
        is ListScreenStatus.Tasks -> {
            viewModel.getTasks(practice = listStatus.practice)
            listStatus.listTasks = tasks
        }
        is ListScreenStatus.Students -> {
            viewModel.getStudents(practice = listStatus.practice, task = listStatus.task)
            listStatus.listStudents = students
            listStatus.saveCheckStudent = { student:StudentDomain -> viewModel.insertStudents(listOf(student))}
        }
    }


    Column(modifier = Modifier.padding(0.dp)) {
        Navbar(
            icoLeftOnClick = icoLeftOnClick,
            iconLeft = iconLeft,
            text = text,
            icoRightOnClick = icoRightOnClick,
            iconRight = iconRight,
            )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
        ) {
            Box() {
                ListItems(
                    onClick = onClickItemList,
                    listStatus = listStatus
                )
            }

            if (listStatus is ListScreenStatus.Practices) {
                Box(
                    contentAlignment = Alignment.BottomEnd, modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                        .fillMaxSize()
                ) {
                    FloatingActionButton(
                        onClick = onClickFloatingButton,
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
}

