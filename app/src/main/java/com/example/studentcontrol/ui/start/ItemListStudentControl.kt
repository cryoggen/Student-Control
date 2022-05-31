package com.example.studentcontrol

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.studentcontrol.ui.navhost.AppStatus

@Composable
fun ItemListStudentControl(practice: String, onClick: () -> Unit = {}, listStatus: AppStatus) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable(onClick = onClick)
    ) {
        listText(practice, appStatus = listStatus)
    }
}

@Composable
fun listText(practice: String, appStatus: AppStatus) {
    var checked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        if (appStatus == AppStatus.STUDENTS) {
            Checkbox(
                checked = checked, onCheckedChange = { checked = !checked }, enabled = true,
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.onSurface, checkmarkColor = MaterialTheme.colors.surface)
            )
        }
        Box(
            modifier = Modifier
                .padding(4.dp)
                .weight(1f)
        ) {

            Text(
                text = practice, style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )

        }
        if (appStatus == AppStatus.STUDENTS) {
            IconButton(onClick = { }) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = stringResource(id = R.string.button_close_description)
                )
            }
        }
    }
}