package com.cryoggen.studentcontrol.presentation.ui.navbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun TextCenterNavbar(text: String) {
    Box(
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text(text = text )
    }
}
