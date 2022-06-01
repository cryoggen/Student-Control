package com.example.studentcontrol

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.studentcontrol.ui.theme.Shapes

@Composable
fun NewPracticeScreenButton(
    text: String, addNewEditField: () -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable(onClick = { addNewEditField() })
            .clip(shape = Shapes.large)
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Text(
                text = text,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.button.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}
