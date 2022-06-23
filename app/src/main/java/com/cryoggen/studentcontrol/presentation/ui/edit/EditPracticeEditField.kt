package com.cryoggen.studentcontrol.presentation.ui.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.R


enum class EditFieldStatus {
    PRACTICES, TASKS, STUDENTS
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NewPracticeEditField(
    value: String,
    onValueChange: (String) -> Unit,
    onDeleteEditField: () -> Unit = {},
    keyboardOptions: KeyboardOptions,
    placeholder: Int,
    editFieldStatus: EditFieldStatus,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val customTextSelectionColors = TextSelectionColors(
        handleColor = MaterialTheme.colors.surface,
        backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.4f)
    )
    Row(
        modifier = Modifier
            .padding(4.dp)

    ) {
        Box(
            modifier = Modifier
                .weight(1f)

        ) {
            CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                TextField(
                    placeholder = { Text(stringResource(placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onPrimary,
                        backgroundColor = MaterialTheme.colors.primary,
                        focusedIndicatorColor = MaterialTheme.colors.surface,
                        placeholderColor = MaterialTheme.colors.surface,
                        cursorColor = MaterialTheme.colors.onPrimary
                    ), shape = MaterialTheme.shapes.large
                )
            }
        }
        if (editFieldStatus != EditFieldStatus.PRACTICES) {
            IconButton(onClick = onDeleteEditField) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = stringResource(id = R.string.button_close_description)
                )
            }

        }
    }
}