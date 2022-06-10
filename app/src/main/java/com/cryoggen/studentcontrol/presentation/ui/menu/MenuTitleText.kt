package com.cryoggen.studentcontrol.presentation.ui.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.House
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.R

@Composable
fun MenuTitleText() {

        Text(
            text = stringResource(id = R.string.menu_practice),
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Medium
            )
        )
        Column(     modifier = Modifier
            .padding(horizontal = 144.dp)
            .padding(bottom = 0.dp, top = 12.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)
        }


}