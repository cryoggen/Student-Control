package com.cryoggen.studentcontrol.presentation.ui.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState

@Composable
fun IconButtons(
    menuType: MenuType,
    onClickIconLeft: () -> Unit,
    onClickIconCenter: () -> Unit,
    onClickIconRight: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(0.dp)
            .height(IntrinsicSize.Min)
    ) {

        when (menuType) {
            MenuType.TASKS -> {
                IconButton(onClick = { onClickIconLeft() }) {
                    Icon(
                        Icons.Filled.Delete,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = stringResource(id = R.string.delete_practice_description)
                    )
                }
                Divider(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .padding(vertical = 12.dp)
                )
                IconButton(onClick = { onClickIconRight() }) {
                    Icon(
                        Icons.Filled.Edit,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = stringResource(id = R.string.edit_practice_description)
                    )
                }
            }

            MenuType.STUDENTS -> {
                IconButton(onClick = { onClickIconLeft() }) {
                    Icon(
                        Icons.Filled.CheckBox,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = stringResource(id = R.string.show_only_checked_students_description)
                    )
                }
                Divider(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .padding(vertical = 12.dp)
                )
                IconButton(onClick = { onClickIconCenter() }) {
                    Icon(
                        Icons.Filled.CheckBoxOutlineBlank,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = stringResource(id = R.string.show_only_unchecked_students_description)
                    )
                }
                Divider(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .padding(vertical = 12.dp)
                )
                IconButton(onClick = { onClickIconRight() }) {
                    Icon(
                        Icons.Filled.ListAlt,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = stringResource(id = R.string.show_all_students_description)
                    )
                }
            }

            MenuType.ARE_YOU_SURE -> {
                IconButton(onClick = { onClickIconLeft() }) {
                    Icon(
                        Icons.Filled.Close,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = stringResource(id = R.string.no_button_text_description)
                    )
                }
                Divider(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .padding(vertical = 12.dp)
                )
                IconButton(onClick = { onClickIconRight() }) {
                    Icon(
                        Icons.Filled.Check,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = stringResource(id = R.string.yes_button_text_description)
                    )
                }
            }
        }
    }
}