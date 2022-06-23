package com.cryoggen.studentcontrol

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cryoggen.domain.models.StudentControlDomain
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState


@Composable
fun ScreenStudentControlList(
    screenStatus: ScreenState,
) {

    when (screenStatus) {
        is ScreenState.Practices -> {
            val listTasks = screenStatus.listPractices
            LazyColumn(modifier = Modifier.padding(vertical = 0.dp)) {
                this.items(items = listTasks) { practice ->
                    ScreenStudentControlItem(
                        item = practice.name,
                        onClick = {
                            screenStatus.itemListOnClickItem(practice.id, practice.name)
                        },
                        screenStatus = screenStatus,
                    )
                }
            }
        }


        is ScreenState.Tasks -> {
            val listTasks = screenStatus.listTasks
            LazyColumn(modifier = Modifier.padding(vertical = 0.dp)) {
                this.items(items = listTasks) { task ->
                    ScreenStudentControlItem(
                        item = task.name,
                        onClick = {
                            screenStatus.itemListOnClickItem(
                                screenStatus.practiceId,
                                screenStatus.practiceName,
                                task.id,
                                task.name
                            )
                        },
                        screenStatus = screenStatus,
                    )
                }
            }
        }
        is ScreenState.Students -> {
            val students = screenStatus.checkedStudentDomainList
            LazyColumn(modifier = Modifier.padding(vertical = 0.dp)) {
                this.items(items = students) { checkedStudent ->

                    var chekStudent by remember { mutableStateOf(false) }
                    chekStudent = checkedStudent.check

                    ScreenStudentControlItem(
                        saveCheckStudent = {
                            screenStatus.saveCheckStudent(
                                StudentControlDomain(
                                    id = checkedStudent.id,
                                    practiceId = checkedStudent.practiceId,
                                    taskId = checkedStudent.taskId,
                                    nameId = checkedStudent.nameId,
                                    check = !chekStudent
                                )
                            )
                            checkedStudent.check = !checkedStudent.check
                            chekStudent = checkedStudent.check

                        },
                        deleteStudent = {
                            screenStatus.deleteTaskStudent(
                                checkedStudent.taskId,
                                checkedStudent.nameId
                            )
                        },
                        item = checkedStudent.name,
                        checked = chekStudent,
                        screenStatus = screenStatus
                    )


                }
            }
        }
        else -> {}
    }

}