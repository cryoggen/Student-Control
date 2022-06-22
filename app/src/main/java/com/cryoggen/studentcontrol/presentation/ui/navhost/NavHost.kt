package com.cryoggen.studentcontrol.presentation.ui.navhost

import android.util.Log
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
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenStudentControl
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.presentation.ui.edit.EditPracticeScreenViewModel
import com.cryoggen.studentcontrol.presentation.ui.edit.EditPracticeScreen
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenStudentControlViewModel
import com.cryoggen.studentcontrol.presentation.ui.list.NavBar


@Composable
fun NavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val screenStudentControlViewModel = hiltViewModel<ScreenStudentControlViewModel>()
    val editPracticeScreenViewModel = hiltViewModel<EditPracticeScreenViewModel>()
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = "list_practices",
        modifier = modifier
    ) {
        composable("list_practices") {

            val itemListOnClickItem = { practiceId: String, practiceName: String ->
                navController.navigate("list_tasks/$practiceId/$practiceName")
            }

            val floatingButtonOnClick = { navController.navigate("new_item_screen") }

            val navBarTitleText = stringResource(id = R.string.navbar_practices_title_text)
            val navBar = NavBar(
                iconLeft = Icons.Filled.House,
                iconRight = Icons.Filled.Person,
                titleText = navBarTitleText
            )

            val screenState =
                ScreenState.Practices(
                    itemListOnClickItem = itemListOnClickItem,
                    floatingButtonOnClick = floatingButtonOnClick,
                    navBar = navBar
                )

            ScreenStudentControl(
                screenState = screenState,
                viewModel = screenStudentControlViewModel
            )
        }

        composable(
            route = "list_tasks/{practiceId}/{practiceName}",
            arguments = listOf(
                navArgument("practiceId") { type = NavType.StringType },
                navArgument("practiceName") { type = NavType.StringType })
        ) { entry ->

            val practiceIdArgument = entry.arguments?.getString("practiceId")
            val practiceNameArgument = entry.arguments?.getString("practiceName")

            val itemListOnClickItem =
                { practiceId: String, practiceName: String, taskId: String, taskName: String ->
                    navController.navigate("list_students/$practiceId/$practiceName/$taskId/$taskName")
                }

            val navBarIconLeftOnClick = { navController.navigate("list_practices") }

            val onEditPracticePressed =
                { navController.navigate("new_item_screen?practiceId=$practiceIdArgument&practiceName=$practiceNameArgument") }
            val navBar = NavBar(
                iconLeft = Icons.Filled.ArrowBack,
                iconRight = Icons.Filled.MoreVert,
                iconLeftOnClick = navBarIconLeftOnClick,
                titleText = practiceNameArgument!!
            )

            val screenState =
                ScreenState.Tasks(
                    practiceId = practiceIdArgument!!,
                    practiceName = practiceNameArgument,
                    itemListOnClickItem = itemListOnClickItem,
                    navBar = navBar,
                    onEditPracticePressed = onEditPracticePressed,
                )

            ScreenStudentControl(
                screenState = screenState,
                viewModel = screenStudentControlViewModel,
            )
        }

        composable(
            "list_students/{practiceId}/{practiceName}/{taskId}/{taskName}",
            arguments = listOf(
                navArgument("practiceId") { type = NavType.StringType },
                navArgument("practiceName") { type = NavType.StringType },
                navArgument("taskId") { type = NavType.StringType },
                navArgument("taskName") { type = NavType.StringType },
            )
        ) { entry ->

            val practiceIdArgument = entry.arguments?.getString("practiceId")
            val practiceNameArgument = entry.arguments?.getString("practiceName")
            val taskIdArgument = entry.arguments?.getString("taskId")
            val taskNameArgument = entry.arguments?.getString("taskName")


            val navBarIconLeftOnClick =
                { navController.navigate("list_tasks/$practiceIdArgument/$practiceNameArgument") }

            val navBar = NavBar(
                iconLeft = Icons.Filled.ArrowBack,
                iconRight = Icons.Filled.MoreVert,
                iconLeftOnClick = navBarIconLeftOnClick,
                titleText = taskNameArgument!!
            )

            val screenState =
                ScreenState.Students(
                    practiceId = practiceIdArgument!!,
                    practiceName = practiceNameArgument!!,
                    taskId = taskIdArgument!!,
                    taskName = taskNameArgument,
                    navBar = navBar,
                )


            ScreenStudentControl(
                screenState = screenState,
                viewModel = screenStudentControlViewModel,
            )
        }



        composable(
            "new_item_screen?practiceId={practiceId}&practiceName={practiceName}",
            arguments = listOf(
                navArgument("practiceId") { defaultValue = ""
                    type = NavType.StringType },
                navArgument("practiceName") { defaultValue = ""
                    type = NavType.StringType})

        ) { backStackEntry ->
            val practiceId = backStackEntry.arguments?.getString("practiceId")
            val practiceName = backStackEntry.arguments?.getString("practiceName")
            val navBarIconLeftOnClick = { navController.navigate("list_practices") }

            val navBarTitleText = if (practiceId == "") {
                stringResource(id = R.string.new_practice)
            } else {
                stringResource(id = R.string.edit_practice)
            }

            val navBar = NavBar(
                iconLeft = Icons.Filled.ArrowBack,
                iconRight = Icons.Filled.Check,
                iconLeftOnClick = navBarIconLeftOnClick,
                titleText = navBarTitleText
            )

            val screenState =
                if (practiceId == "") {
                    ScreenState.NewPractice(
                        navBar = navBar,
                    )
                } else {

                    ScreenState.EditPractice(
                        navBar = navBar,
                        practiceId = practiceId!!,
                        practiceName = practiceName!!
                    )
                }

            EditPracticeScreen(
                screenState = screenState,
                viewModel = editPracticeScreenViewModel
            )
        }


    }
}





