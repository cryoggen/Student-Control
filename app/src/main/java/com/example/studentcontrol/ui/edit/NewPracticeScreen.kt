package com.example.studentcontrol

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studentcontrol.ui.edit.EditFieldStatus
import com.example.studentcontrol.ui.edit.NewPracticeEditField
import com.example.studentcontrol.ui.theme.StudentControlTheme


@Composable
fun NewPracticeScreen() {
    var practiceNameInput by remember { mutableStateOf("") }
    var tasks = remember { mutableStateListOf("") }
    var students = remember { mutableStateListOf("") }
    Column(
        modifier = Modifier
            .padding(16.dp)

    ) {

        NewPracticeTextTitleEdit(text = stringResource(id = R.string.practice_name))

        NewPracticeEditField(
            value = practiceNameInput,
            onValueChange = { practiceNameInput = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            placeholder = R.string.new_practice_name_value,
            editFieldStatus = EditFieldStatus.PRACTICES,
        )

        Spacer(Modifier.height(32.dp))

        NewPracticeTextTitleEdit(text = stringResource(id = R.string.tasks))

        for (i in 0 until tasks.size) {
            NewPracticeEditField(
                value = tasks[i],
                onValueChange = { tasks[i] = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                placeholder = R.string.new_task_name_value,
                editFieldStatus = EditFieldStatus.TASKS,
            onDeleteEditField = { tasks.removeAt(i)}
            )
        }
        NewPracticeScreenButton(
            text = stringResource(id = R.string.new_task_name_text_button),
            addNewEditField = { tasks.add("") }
        )

        Spacer(Modifier.height(32.dp))

        NewPracticeTextTitleEdit(text = stringResource(id = R.string.students))
        for (i in 0 until students.size) {
            NewPracticeEditField(
                value = students[i],
                onValueChange = { students[i] = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                placeholder = R.string.new_student_name_value,
                editFieldStatus = EditFieldStatus.STUDENTS,
                onDeleteEditField = { students.removeAt(i)}
            )
        }
        NewPracticeScreenButton(
            text = stringResource(id = R.string.new_student_text_button),
            addNewEditField = { students.add("") }
        )
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun NewPracticeScreenPreview() {
    StudentControlTheme {
        NewPracticeScreen()
    }
}