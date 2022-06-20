package com.cryoggen.studentcontrol.presentation.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState
import com.cryoggen.studentcontrol.presentation.ui.theme.Shapes

@Composable
fun MenuScreen(menuClose: () -> Unit, screenState: ScreenState) {
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
                .clickable(enabled =false, onClick = {})
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 8.dp, top = 16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                MenuTitleText()
                IconButtons(screenState = screenState)
            }
        }
    }
}


