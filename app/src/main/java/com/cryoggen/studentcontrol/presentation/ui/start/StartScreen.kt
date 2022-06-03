package com.cryoggen.studentcontrol.presentation.ui.start

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.R
import com.cryoggen.studentcontrol.presentation.ui.navhost.ListStatus
import com.cryoggen.studentcontrol.presentation.ui.navhost.testListPractices
import com.cryoggen.studentcontrol.ListItems
import com.cryoggen.studentcontrol.presentation.viewmodel.StudentControlViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StartScreen(
    listItems: List<String> = testListPractices,
    onClickItemList: () -> Unit,
    onClickFloatingButton: () -> Unit = {},
    appStatus: ListStatus,
//    viewModel: StudentControlViewModel = viewModel()
) {
//    viewModel.Test()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        Box() {
            ListItems(
                practices = listItems,
                onClick = onClickItemList, listStatus = appStatus,

                )
        }

        if (appStatus == ListStatus.PRACTICES) {
            Box(
                contentAlignment = Alignment.BottomEnd, modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 16.dp)
                    .fillMaxSize()
            ) {
                FloatingActionButton(
                    onClick = onClickFloatingButton,
                    backgroundColor = MaterialTheme.colors.surface
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.button_add_new_practice_description)
                    )
                }
            }
        }
    }


}