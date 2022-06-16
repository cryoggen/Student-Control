package com.cryoggen.studentcontrol.presentation.ui.navbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState

@Composable
fun IconRightNavbar(
    icoRightOnClick: () -> Unit,
    iconRight: ImageVector,
    screenState: ScreenState
) {


    Box(
        modifier = Modifier
            .padding(0.dp)
    ) {

        IconButton(onClick = { icoRightOnClick() }) {
            if (screenState is ScreenState.NewPractice) {
                Icon(
                    iconRight,
                    tint = MaterialTheme.colors.secondary,
                    contentDescription = stringResource(id = R.string.nav_bar_menu_description)
                )
            } else {
                Icon(
                    iconRight,
                    contentDescription = stringResource(id = R.string.nav_bar_menu_description)
                )
            }
        }
    }
}
