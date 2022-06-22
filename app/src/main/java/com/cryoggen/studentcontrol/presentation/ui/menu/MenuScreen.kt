package com.cryoggen.studentcontrol.presentation.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState
import com.cryoggen.studentcontrol.presentation.ui.theme.Shapes

enum class MenuType {
    TASKS, STUDENTS, ARE_YOU_SURE
}


@Composable
fun MenuScreen(
    menuClose: () -> Unit, menuType: MenuType, onClickIconLeft: () -> Unit = {},
    onClickIconCenter: () -> Unit = {},
    onClickIconRight: () -> Unit = {}
) {

    var titleText = ""
    titleText = when (menuType) {
        MenuType.TASKS -> {
            stringResource(id = R.string.menu_tasks)
        }
        MenuType.STUDENTS -> {
            stringResource(id = R.string.menu_sort_students)
        }
        MenuType.ARE_YOU_SURE -> {
            stringResource(id = R.string.menu_are_you_sure)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.onSecondary)
            .clickable(onClick = menuClose)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clip(shape = Shapes.large)
                .align(alignment = Alignment.Center)
                .background(color = MaterialTheme.colors.onSurface)
                .clickable(enabled = false, onClick = {})
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 8.dp, top = 16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                MenuTitleText(titleText = titleText)
                IconButtons(
                    menuType = menuType,
                    onClickIconLeft = onClickIconLeft,
                    onClickIconCenter = onClickIconCenter,
                    onClickIconRight = onClickIconRight
                )
            }
        }
    }
}


