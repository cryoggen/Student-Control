package com.cryoggen.studentcontrol

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.presentation.ui.edit.EditFieldStatus
import com.cryoggen.studentcontrol.presentation.ui.edit.EditPracticeScreenViewModel
import com.cryoggen.studentcontrol.presentation.ui.edit.NewPracticeEditField
import com.cryoggen.studentcontrol.presentation.ui.navbar.Navbar

@Composable
fun NewPracticeScreen(
    icoLeftOnClick: () -> Unit,
    iconLeft: ImageVector,
    text: String,
    iconRight: ImageVector,
    icoRightOnClick: () -> Unit,
    tintIconRight: Color,
    viewModel: EditPracticeScreenViewModel
) {
    var practice by remember { mutableStateOf("") }
    val tasks = remember { mutableStateListOf("") }
    val studentNames = remember { mutableStateListOf("") }

    Column() {
        Navbar(
            icoLeftOnClick = icoLeftOnClick,
            iconLeft = iconLeft,
            text = text,
            iconRight = iconRight,
            icoRightOnClick = {
                viewModel.insertStudents(practice = practice, tasks = tasks, studentNames = studentNames)
                icoRightOnClick()},
            tintIconRight = tintIconRight
        )
        Column(
            modifier = Modifier
                .padding(16.dp)

        ) {

            NewPracticeTextTitleEdit(text = stringResource(id = R.string.practice_name))

            NewPracticeEditField(
                value = practice,
                onValueChange = { practice = it },
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
                    onDeleteEditField = { tasks.removeAt(i) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    placeholder = R.string.new_task_name_value,
                    editFieldStatus = EditFieldStatus.TASKS
                )
            }
            NewPracticeScreenButton(
                text = stringResource(id = R.string.new_task_name_text_button),
                addNewEditField = { tasks.add("") }
            )

            Spacer(Modifier.height(32.dp))

            NewPracticeTextTitleEdit(text = stringResource(id = R.string.students))
            for (i in 0 until studentNames.size) {
                NewPracticeEditField(
                    value = studentNames[i],
                    onValueChange = { studentNames[i] = it },
                    onDeleteEditField = { studentNames.removeAt(i) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    placeholder = R.string.new_student_name_value,
                    editFieldStatus = EditFieldStatus.STUDENTS
                )
            }
            NewPracticeScreenButton(
                text = stringResource(id = R.string.new_student_text_button),
                addNewEditField = { studentNames.add("") }
            )
        }
    }


}

