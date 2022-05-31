package com.example.studentcontrol

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentcontrol.ui.navhost.AppStatus
import com.example.studentcontrol.ui.navhost.testListPractices


@Composable
fun ListItems(
    practices: List<String> = testListPractices,
    onClick: () -> Unit,
    listStatus: AppStatus
) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = practices) { practice ->
            ItemListStudentControl(practice = practice, onClick = onClick, listStatus = listStatus)
        }
    }
}