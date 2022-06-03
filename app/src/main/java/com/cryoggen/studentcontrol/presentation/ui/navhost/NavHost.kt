package com.cryoggen.studentcontrol.presentation.ui.navhost

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cryoggen.studentcontrol.NewPracticeScreen
import com.cryoggen.studentcontrol.presentation.ui.start.StartScreen
import com.cryoggen.studentcontrol.presentation.ui.navbar.Navbar
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.presentation.ui.theme.StudentControlTheme

enum class ListStatus {
    PRACTICES, TASKS, STUDENTS
}

val testListPractices =
    listOf(
        "Русский язык",
        "Алгебра",
        "Природоведенье",
        "Литература",
        "Английский язык",
        "Русский язык",
        "Алгебра",
        "Природоведенье",
        "Литература",
        "Английский язык",
        "Русский язык",
        "Алгебра",
        "Природоведенье",
        "Литература",
        "Английский язык"
    )
val testListTasks =
    listOf("Контрольная", "Домашняя Работа", "Зачёт", "Экзамент", "Английский язык")
val testListStudents =
    listOf("Иванов", "Петров", "Сидоров", "Круглов", "Светлов")

@Composable
fun NavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = "list_practices",
        modifier = modifier
    ) {
        composable("list_practices") {
            Column(modifier = Modifier.padding(0.dp)) {
                Navbar(icoLeftOnClick = {},
                    iconLeft = Icons.Filled.House,
                    text = stringResource(id = R.string.navbar_practices_text),
                    iconRight = Icons.Filled.Person,
                    icoRightOnClick = {})
                StartScreen(
                    listItems = testListPractices,
                    onClickItemList = { navController.navigate("list_tasks") },
                    onClickFloatingButton = { navController.navigate("new_item_screen") },
                    appStatus = ListStatus.PRACTICES,
                )
                Spacer(Modifier.height(64.dp))
            }
        }

        composable("list_tasks") {
            Column() {
                Navbar(icoLeftOnClick = { navController.navigate("list_practices") },
                    iconLeft = Icons.Filled.ArrowBack,
                    text = stringResource(id = R.string.navbar_tasks_text),
                    iconRight = Icons.Filled.MoreVert,
                    icoRightOnClick = {})
                StartScreen(
                    listItems = testListTasks,
                    onClickItemList = {
                        navController.navigate("list_students")
                    },
                    appStatus = ListStatus.TASKS
                )
            }
        }
        composable("list_students") {
            Column() {
                Navbar(icoLeftOnClick = { navController.navigate("list_tasks") },
                    iconLeft = Icons.Filled.ArrowBack,
                    text = stringResource(id = R.string.navbar_students_text),
                    iconRight = Icons.Filled.MoreVert,
                    icoRightOnClick = {})
                StartScreen(
                    listItems = testListStudents,
                    onClickItemList = {

                    }, appStatus = ListStatus.STUDENTS
                )
            }
        }
        composable("new_item_screen") {
            Column() {
                Navbar(
                    icoLeftOnClick = { navController.navigate("list_practices") },
                    iconLeft = Icons.Filled.ArrowBack,
                    text = stringResource(id = R.string.new_practice),
                    iconRight = Icons.Filled.Check,
                    icoRightOnClick = {},
                    tintIconRight = MaterialTheme.colors.secondary
                )
                NewPracticeScreen()
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun StartScreenPreview() {
    StudentControlTheme {
        Column() {
            Navbar(
                icoLeftOnClick = {},
                iconLeft = Icons.Filled.ArrowBack,
                text = stringResource(id = R.string.new_practice),
                iconRight = Icons.Filled.MoreVert,
                icoRightOnClick = {},
                tintIconRight = MaterialTheme.colors.secondary
            )
            StartScreen(
                listItems = testListPractices,
                onClickItemList = { },
                onClickFloatingButton = { },
                appStatus = ListStatus.PRACTICES
            )

        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun NewPracticeScreenPreview() {
    Column() {
        Navbar(
            icoLeftOnClick = { },
            iconLeft = Icons.Filled.ArrowBack,
            text = stringResource(id = R.string.new_practice),
            iconRight = Icons.Filled.Check,
            icoRightOnClick = {},
            tintIconRight = MaterialTheme.colors.secondary
        )
        NewPracticeScreen()
    }
}