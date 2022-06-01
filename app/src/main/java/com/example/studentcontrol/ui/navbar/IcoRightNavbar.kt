package com.example.studentcontrol.ui.navbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.studentcontrol.R

@Composable
fun IcoRightNavbar(icoRightOnClick: () -> Unit, iconRight: ImageVector, tintIconRight: Color) {
    Box(
        modifier = Modifier
            .padding(0.dp)
    ) {
        IconButton(onClick = icoRightOnClick) {
            Icon(
                iconRight,
                tint = tintIconRight,
                contentDescription = stringResource(id = R.string.nav_bar_menu_description)
            )
        }
    }
}