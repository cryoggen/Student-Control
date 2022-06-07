package com.cryoggen.studentcontrol

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
import com.cryoggen.studentcontrol.presentation.ui.navhost.ListScreenStatus


@Composable
fun ItemListStudentControl(
    item: String,
    checkedStudent: Boolean = false,
    onClick: () -> Unit = {},
    listStatus: ListScreenStatus
) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable(onClick = onClick)
    ) {


        var checked by remember { mutableStateOf(checkedStudent) }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {
            if (listStatus is ListScreenStatus.Students) {
                Checkbox(
                    checked = checked, onCheckedChange = { checked = !checked }, enabled = true,
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colors.onSurface,
                        checkmarkColor = MaterialTheme.colors.surface
                    )
                )
            }
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f)
            ) {

                Text(
                    text = item, style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )

            }
            if (listStatus is ListScreenStatus.Students) {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.button_close_description)
                    )
                }
            }
        }
    }
}