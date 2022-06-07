package com.cryoggen.studentcontrol.presentation.ui.navhost

import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cryoggen.domain.models.StudentDomain
import com.cryoggen.studentcontrol.NewPracticeScreen
import com.cryoggen.studentcontrol.presentation.ui.list.ListScreen
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.presentation.ui.edit.EditPracticeScreenViewModel
import com.cryoggen.studentcontrol.presentation.ui.list.ListScreenViewModel

sealed class ListScreenStatus {


    data class Practices(var listPractices: List<String> = listOf()) : ListScreenStatus()

    data class Tasks(val practice: String = "", var listTasks: List<String> = listOf()) :
        ListScreenStatus()

    data class Students(
        val practice: String = "",
        val task: String = "",
        var listStudents: List<StudentDomain> = listOf(),
        var saveCheckStudent: (StudentDomain) -> Unit = {},
        val deleteStudent: () -> Unit = {}
    ) : ListScreenStatus()
}


@Composable
fun NavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val listScreenViewModel = hiltViewModel<ListScreenViewModel>()
    val editPracticeScreenViewModel = hiltViewModel<EditPracticeScreenViewModel>()
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = "list_practices",
        modifier = modifier
    ) {
        composable("list_practices") {
            ListScreen(
                icoLeftOnClick = {},
                iconLeft = Icons.Filled.House,
                text = stringResource(id = R.string.navbar_practices_text),
                iconRight = Icons.Filled.Person,
                icoRightOnClick = {},
                onClickItemList = { practice: String, _ ->
                    navController.navigate("list_tasks/$practice")
                },
                onClickFloatingButton = { navController.navigate("new_item_screen") },
                listStatus = ListScreenStatus.Practices(),
                viewModel = listScreenViewModel
            )
        }

        composable(
            route = "list_tasks/{practice}",
            arguments = listOf(navArgument("practice") { type = NavType.StringType })
        ) { entry ->

            val practice = entry.arguments?.getString("practice")

            ListScreen(
                icoLeftOnClick = { navController.navigate("list_practices") },
                iconLeft = Icons.Filled.ArrowBack,
                text = stringResource(id = R.string.navbar_tasks_text),
                iconRight = Icons.Filled.MoreVert,
                icoRightOnClick = {},
                onClickItemList = { practice: String, task: String ->
                    navController.navigate("list_students/$practice/$task")
                },
                listStatus = ListScreenStatus.Tasks(practice = practice!!),
                viewModel = hiltViewModel<ListScreenViewModel>(),
            )
        }
        composable(
            "list_students/{practice}/{task}",
            arguments = listOf(
                navArgument("task") { type = NavType.StringType },
                navArgument("practice") { type = NavType.StringType })
        ) { entry ->
            val practice = entry.arguments?.getString("practice")
            val task = entry.arguments?.getString("task")
            ListScreen(
                icoLeftOnClick = { navController.navigate("list_tasks/$practice") },
                iconLeft = Icons.Filled.ArrowBack,
                text = stringResource(id = R.string.navbar_students_text),
                iconRight = Icons.Filled.MoreVert,
                icoRightOnClick = {},
                onClickItemList = { _, _ -> },
                listStatus = ListScreenStatus.Students(practice = practice!!, task = task!!),
                viewModel = listScreenViewModel,
            )
        }

        composable("new_item_screen") {
            NewPracticeScreen(
                icoLeftOnClick = { navController.navigate("list_practices") },
                iconLeft = Icons.Filled.ArrowBack,
                text = stringResource(id = R.string.new_practice),
                iconRight = Icons.Filled.Check,
                icoRightOnClick = {
                    navController.navigate("list_practices")
                },
                tintIconRight = MaterialTheme.colors.secondary,
                viewModel = editPracticeScreenViewModel
            )

        }
    }
}





