package com.cryoggen.studentcontrol

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState


@Composable
fun ScreenStudentControlList(
    screenStatus: ScreenState,
) {

    when (screenStatus) {
        is ScreenState.Practices -> {
            LazyColumn(modifier = Modifier.padding(vertical = 0.dp)) {
                this.items(items = screenStatus.listPractices) { practice ->
                    ScreenStudentControlItem(
                        item = practice,
                        onClick = { screenStatus.itemListOnClickItem(practice) },
                        screenStatus = screenStatus,
                    )
                }
            }
        }


        is ScreenState.Tasks -> {
            LazyColumn(modifier = Modifier.padding(vertical = 0.dp)) {
                this.items(items = screenStatus.listTasks) { task ->
                    ScreenStudentControlItem(
                        item = task,
                        onClick = { screenStatus.itemListOnClickItem(screenStatus.practice, task) },
                        screenStatus = screenStatus,
                    )
                }
            }
        }
        is ScreenState.Students -> {
            LazyColumn(modifier = Modifier.padding(vertical = 0.dp)) {
                this.items(items = screenStatus.listStudents) { student ->

                    var chekStudent by remember { mutableStateOf(student.check) }


                    val saveCheckStudent = {
                        screenStatus.saveCheckStudent(student)
                        chekStudent = !chekStudent
                    }

                    val deleteStudent = { screenStatus.deleteStudent(student) }


                    student.check = chekStudent
                    screenStatus.saveCheckStudent(student)

                    ScreenStudentControlItem(
                        saveCheckStudent = saveCheckStudent,
                        deleteStudent = deleteStudent,
                        item = student.name,
                        checked = chekStudent,
                        screenStatus = screenStatus
                    )
                }
            }
        }
        is ScreenState.Edit -> {}
    }


}