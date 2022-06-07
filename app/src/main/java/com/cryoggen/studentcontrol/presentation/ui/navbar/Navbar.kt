package com.cryoggen.studentcontrol.presentation.ui.navbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun Navbar(
    icoLeftOnClick: () -> Unit,
    iconLeft: ImageVector,
    text: String,
    icoRightOnClick: () -> Unit,
    iconRight: ImageVector,
    tintIconRight: Color = MaterialTheme.colors.onPrimary,
) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        IcoLeftNavbar(icoLeftOnClick = icoLeftOnClick, iconLeft = iconLeft)
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextCenterNavbar(text = text)
        }
        IcoRightNavbar(
            icoRightOnClick = icoRightOnClick,
            iconRight = iconRight,
            tintIconRight = tintIconRight,
        )

    }

}