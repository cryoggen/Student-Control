package com.example.studentcontrol.ui.navbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.House
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.studentcontrol.R

@Composable
fun IcoLeftNavbar(icoLeftOnClick: () -> Unit, iconLeft: ImageVector) {
    Box(
        modifier = Modifier
            .padding(0.dp)
    ) {
        if (iconLeft != Icons.Filled.House) {
            IconButton(onClick = icoLeftOnClick) {
                Icon(
                    iconLeft,
                    contentDescription = stringResource(id = R.string.nav_bar_arrow_back_description)
                )
            }
        } else {
            IconButton(onClick = { }, enabled = false) {

            }
        }

    }
}