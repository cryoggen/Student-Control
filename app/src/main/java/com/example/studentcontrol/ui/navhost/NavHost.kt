package com.example.studentcontrol.ui.navhost

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studentcontrol.NewPracticeScreen
import com.example.studentcontrol.StartScreen

enum class AppStatus {
    PRACTICES, TASKS, STUDENTS, NEW_PRACTICE
}

val testListPractices =
    listOf("Русский язык", "Алгебра", "Природоведенье", "Литература", "Английский язык")
val testListTasks =
    listOf("Контрольная", "Домашняя Работа", "Зачёт", "Экзамент", "Английский язык")
val testListStudents =
    listOf("Иванов", "Петров", "Сидоров", "Круглов", "Светлов")

@Composable
fun NavHost( modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = "list_practices",
        modifier = modifier
    ) {
        composable("list_practices") {
            StartScreen(
                listItems = testListPractices,
                onClickItemList = { navController.navigate("list_tasks") },
                onClickFloatingButton = { navController.navigate("new_item_screen") },
                appStatus = AppStatus.PRACTICES
            )

        }
        composable("list_tasks") {
            StartScreen(
                listItems = testListTasks,
                onClickItemList = {
                    navController.navigate("list_students")
                },
                appStatus = AppStatus.TASKS
            )
        }
        composable("list_students") {
            StartScreen(
                listItems = testListStudents,
                onClickItemList = {

                }, appStatus = AppStatus.STUDENTS
            )
        }

        composable("new_item_screen") {
            NewPracticeScreen()
        }
    }
}