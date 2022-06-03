package com.cryoggen.studentcontrol

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.presentation.ui.navhost.ListStatus
import com.cryoggen.studentcontrol.presentation.ui.navhost.testListPractices


@Composable
fun ListItems(
    practices: List<String> = testListPractices,
    onClick: () -> Unit,
    listStatus: ListStatus
) {
    LazyColumn(modifier = Modifier.padding(vertical = 0.dp)) {
        items(items = practices) { practice ->
            ItemListStudentControl(practice = practice, onClick = onClick, listStatus = listStatus)
        }
    }

}