package com.cryoggen.studentcontrol.presentation.ui.edit

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState
import com.cryoggen.studentcontrol.presentation.ui.navbar.Navbar

@Composable
fun EditPracticeScreen(
    viewModel: EditPracticeScreenViewModel,
    screenState: ScreenState.Edit
) {
    var practice by remember { mutableStateOf("") }
    val tasks = remember { mutableStateListOf("") }
    val studentNames = remember { mutableStateListOf("") }

    var savePracticeButtonClicked by remember {
        mutableStateOf(false)
    }

    screenState.navBar.iconRightOnClick = {
        savePracticeButtonClicked = true
    }


    when {

        practice == "" && savePracticeButtonClicked -> {
            Log.d("11111", "test")
            SnackBar(stringResource(id = R.string.practice_not_introduced), stopSnackBar =  {
                savePracticeButtonClicked = false
            })

        }
        tasks[0] == "" && savePracticeButtonClicked -> {
            SnackBar(stringResource(id = R.string.tasks_not_introduced), stopSnackBar =  {
                savePracticeButtonClicked = false
            })

        }
        studentNames[0] == "" && savePracticeButtonClicked -> {
            SnackBar(stringResource(id = R.string.students_not_introduced), stopSnackBar =  {
                savePracticeButtonClicked = false
            })

        }
        savePracticeButtonClicked -> {
            viewModel.insertStudents(
                practice = practice,
                tasks = tasks,
                studentNames = studentNames
            )
           screenState.navBar.iconLeftOnClick()
            Log.d("11111", "test")
            savePracticeButtonClicked = false
        }

    }

    Column() {
        Navbar(
            screenState = screenState
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())

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


