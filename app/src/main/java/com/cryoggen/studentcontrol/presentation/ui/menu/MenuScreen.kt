package com.cryoggen.studentcontrol.presentation.ui.menu

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.presentation.ui.theme.Shapes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class MenuType {
    TASKS, STUDENTS, ARE_YOU_SURE
}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MenuScreen(
    menuClose: () -> Unit, menuType: MenuType, onClickIconLeft: () -> Unit = {},
    onClickIconCenter: () -> Unit = {},
    onClickIconRight: () -> Unit = {}
) {

    var titleText = ""
    var menuShown by remember { mutableStateOf(false) }
    var stateMenu by remember { mutableStateOf(true) }
    var visibleMenu by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

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

    if (stateMenu) {
        coroutineScope.launch {
            menuShown = true
        }
        stateMenu = false
    }

    if (visibleMenu) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.onSecondary)
                .clickable(onClick = {
                    coroutineScope.launch {
                        menuShown = false
                        delay(300)
                        visibleMenu = false
                        menuClose()
                    }
                }),
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = menuShown,
                enter = expandHorizontally(),
                exit = shrinkHorizontally()
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(shape = Shapes.large)
                        .background(color = MaterialTheme.colors.onSurface)
                        .clickable(enabled = false, onClick = {}),

                    ) {

                    Column(
                        modifier = Modifier
                            .padding(bottom = 8.dp, top = 16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                        MenuTitleText(titleText = titleText)
                        IconButtons(
                            menuType = menuType,
                            onClickIconLeft = {
                                coroutineScope.launch {
                                    menuShown = false
                                    delay(250)
                                    visibleMenu = false
                                    onClickIconLeft()
                                }
                            },
                            onClickIconCenter = {
                                coroutineScope.launch {
                                    menuShown = false
                                    delay(250)
                                    visibleMenu = false
                                    onClickIconCenter()
                                }
                            },
                            onClickIconRight = {
                                coroutineScope.launch {
                                    menuShown = false
                                    delay(250)
                                    visibleMenu = false
                                    onClickIconRight()
                                }
                            }
                        )
                    }
                }
            }
        }
    }

}


