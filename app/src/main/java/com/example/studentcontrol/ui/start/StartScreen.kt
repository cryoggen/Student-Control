package com.example.studentcontrol

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.studentcontrol.ui.navhost.AppStatus
import com.example.studentcontrol.ui.navhost.testListPractices


@Composable
fun StartScreen(
    listItems: List<String> = testListPractices,
    onClickItemList: () -> Unit,
    onClickFloatingButton: () -> Unit ={},
    appStatus: AppStatus
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ListItems(
            practices = listItems,
            onClick = onClickItemList, listStatus = appStatus,

            )
    }
    if (appStatus == AppStatus.PRACTICES) {
        Box(
            contentAlignment = Alignment.BottomEnd, modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .fillMaxSize()
        ) {
            FloatingActionButton(onClick = onClickFloatingButton, backgroundColor = MaterialTheme.colors.surface) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.button_add_new_practice_description)
                )
            }
        }
    }


}