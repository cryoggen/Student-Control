package com.cryoggen.studentcontrol.presentation.ui.edit


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp



@Composable
fun NewPracticeTextTitleEdit(
    text: String
) {
    Box(
        modifier = Modifier
            .padding(0.dp)

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}