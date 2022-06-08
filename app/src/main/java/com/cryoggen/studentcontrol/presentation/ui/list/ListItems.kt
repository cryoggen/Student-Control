package com.cryoggen.studentcontrol

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.presentation.ui.navhost.ListScreenStatus


@Composable
fun ListItems(
    onClick: (String, String) -> Unit,
    listStatus: ListScreenStatus
) {

    when (listStatus) {
        is ListScreenStatus.Practices -> {
            LazyColumn(modifier = Modifier.padding(vertical = 0.dp)) {
                this.items(items = listStatus.listPractices) { practice ->
                    ItemListStudentControl(
                        item = practice,
                        onClick = { onClick(practice, "") },
                        listStatus = listStatus,
                    )
                }
            }
        }


        is ListScreenStatus.Tasks -> {
            LazyColumn(modifier = Modifier.padding(vertical = 0.dp)) {
                this.items(items = listStatus.listTasks) { task ->
                    ItemListStudentControl(
                        item = task,
                        onClick = { onClick(listStatus.practice, task) },
                        listStatus = listStatus,
                    )
                }
            }
        }
        is ListScreenStatus.Students -> {

            LazyColumn(modifier = Modifier.padding(vertical = 0.dp)) {
                this.items(items = listStatus.listStudents) { student ->
                    var chekStudent by remember { mutableStateOf(student.check) }
                    student.check = chekStudent
                    listStatus.saveCheckStudent(student)
                    ItemListStudentControl(
                        item = student.name,
                        checked = chekStudent,
                        saveCheckStudent = {
                            chekStudent = ! chekStudent
                            },
                        listStatus = listStatus
                    )
                }
            }
        }
    }


}