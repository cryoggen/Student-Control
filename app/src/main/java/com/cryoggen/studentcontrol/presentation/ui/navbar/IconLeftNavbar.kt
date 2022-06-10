package com.cryoggen.studentcontrol.presentation.ui.navbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.House
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState

@Composable
fun IconLeftNavbar(iconLeftOnClick: () -> Unit, iconLeft: ImageVector, screenState: ScreenState) {
    Box(
        modifier = Modifier
            .padding(0.dp)
    ) {
        if (screenState is ScreenState.Practices) {
            IconButton(onClick = { }, enabled = false) {

            }

        } else {
            IconButton(onClick = iconLeftOnClick) {
                Icon(
                    iconLeft,
                    contentDescription = stringResource(id = R.string.nav_bar_arrow_back_description)
                )
            }
        }

    }
}