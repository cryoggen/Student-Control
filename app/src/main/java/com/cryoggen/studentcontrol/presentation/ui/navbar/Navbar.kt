package com.cryoggen.studentcontrol.presentation.ui.navbar

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState


@Composable
fun Navbar(
    screenState: ScreenState,
) {

    val navBar = when (screenState) {
        is ScreenState.Practices -> {
            screenState.navBar
        }
        is ScreenState.Tasks -> {
            screenState.navBar
        }
        is ScreenState.Students -> {
            screenState.navBar
        }
        is ScreenState.Students -> {
            screenState.navBar
        }
        is ScreenState.Edit -> {
            screenState.navBar
        }
    }



    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        IconLeftNavbar(
            iconLeftOnClick = navBar.iconLeftOnClick,
            iconLeft = navBar.iconLeft,
            screenState = screenState
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextCenterNavbar(text = navBar.titleText)
        }
        IconRightNavbar(
            icoRightOnClick = navBar.iconRightOnClick,
            iconRight = navBar.iconRight,
            screenState = screenState
        )

    }

}