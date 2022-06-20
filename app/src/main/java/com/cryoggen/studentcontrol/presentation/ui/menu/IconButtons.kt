package com.cryoggen.studentcontrol.presentation.ui.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.House
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
fun IconButtons( screenState: ScreenState) {
    Row(
        modifier = Modifier
            .padding(0.dp)
            .height(IntrinsicSize.Min)
    ) {
        when(screenState){
            is ScreenState.Tasks -> {
                IconButton(onClick = {screenState.onDeletePracticePressed()}) {
                    Icon(
                        Icons.Filled.Delete,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = stringResource(id = R.string.nav_bar_menu_description)
                    )
                }
                Divider(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .padding(vertical = 12.dp)
                )
                IconButton(onClick = screenState.onEditPracticePressed) {
                    Icon(
                        Icons.Filled.Edit,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = stringResource(id = R.string.nav_bar_menu_description)
                    )
                }
            }
            is ScreenState.Students -> {}
            else -> {}
        }

    }
}