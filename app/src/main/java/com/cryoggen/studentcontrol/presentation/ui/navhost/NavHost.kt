package com.cryoggen.studentcontrol.presentation.ui.navhost

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

            val itemListOnClickItem = { practice: String ->
                navController.navigate("list_tasks/$practice")
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
            route = "list_tasks/{practice}",
            arguments = listOf(navArgument("practice") { type = NavType.StringType })
        ) { entry ->

            val practice = entry.arguments?.getString("practice")
            val itemListOnClickItem = { practice: String, task: String ->
                navController.navigate("list_students/$practice/$task")
            }
            val navBarIconLeftOnClick = { navController.navigate("list_practices") }

            val navBar = NavBar(
                iconLeft = Icons.Filled.ArrowBack,
                iconRight = Icons.Filled.MoreVert,
                iconLeftOnClick = navBarIconLeftOnClick,
                titleText = practice!!
            )

            val screenState =
                ScreenState.Tasks(
                    practice = practice!!,
                    itemListOnClickItem = itemListOnClickItem,
                    navBar = navBar
                )

            ScreenStudentControl(
                screenState = screenState,
                viewModel = hiltViewModel<ScreenStudentControlViewModel>(),
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
            val navBarIconLeftOnClick = { navController.navigate("list_tasks/$practice") }

            val navBar = NavBar(
                iconLeft = Icons.Filled.ArrowBack,
                iconRight = Icons.Filled.MoreVert,
                iconLeftOnClick = navBarIconLeftOnClick,
                titleText = task!!
            )

            val screenState =
                ScreenState.Students(
                    practice = practice!!,
                    task = task!!,
                    navBar = navBar
                )


            ScreenStudentControl(
                screenState = screenState,
                viewModel = screenStudentControlViewModel,
            )
        }

        composable("new_item_screen") {

            val navBarIconLeftOnClick = { navController.navigate("list_practices") }
            val navBarTitleText = stringResource(id = R.string.new_practice)

            val navBar = NavBar(
                iconLeft = Icons.Filled.ArrowBack,
                iconRight = Icons.Filled.Check,
                iconLeftOnClick = navBarIconLeftOnClick,
                titleText = navBarTitleText
            )

            val screenState =
                ScreenState.Edit(
                    navBar = navBar
                )

            EditPracticeScreen(
                screenState = screenState,
                viewModel = editPracticeScreenViewModel
            )
        }

//        composable("menu_list_task") {
//            MenuScreen()
//        }
    }
}





