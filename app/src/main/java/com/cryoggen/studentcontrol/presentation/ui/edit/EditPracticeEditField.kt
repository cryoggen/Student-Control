package com.cryoggen.studentcontrol.presentation.ui.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.R


enum class EditFieldStatus {
    PRACTICES, TASKS, STUDENTS
}

@Composable
fun NewPracticeEditField(
    value: String,
    onValueChange: (String) -> Unit,
    onDeleteEditField: () -> Unit = {},
    keyboardOptions: KeyboardOptions,
    placeholder: Int,
    editFieldStatus: EditFieldStatus,
) {

    Row(
        modifier = Modifier
            .padding(4.dp)

    ) {
            Box(
                modifier = Modifier
                    .weight(1f)

            ) {
                TextField(
                    placeholder = { Text(stringResource(placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = keyboardOptions,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onPrimary,
                        backgroundColor = MaterialTheme.colors.primary,
                        placeholderColor = MaterialTheme.colors.surface,
                    ), shape = MaterialTheme.shapes.large
                )
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