package com.cryoggen.studentcontrol

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cryoggen.studentcontrol.presentation.ui.list.ScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ScreenStudentControlItem(
    item: String,
    checked: Boolean = false,
    onClick: () -> Unit = {},
    screenStatus: ScreenState,
    saveCheckStudent: () -> Unit = {},
    deleteStudent: () -> Unit = {},

    ) {
    val clickableEnabled = screenStatus !is ScreenState.Students

    val coroutineScope = rememberCoroutineScope()

    var itemShown by remember { mutableStateOf(false) }
    var stateItemAnimation by remember { mutableStateOf(true) }

    if (stateItemAnimation) {
        coroutineScope.launch {
            itemShown = true
            stateItemAnimation = false
        }

    }


    AnimatedVisibility(
        visible = itemShown,
        enter = expandHorizontally(),
        exit = shrinkHorizontally()
    ) {

        Card(
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clickable(onClick = onClick, enabled = clickableEnabled)
        ) {


            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)

            ) {
                if (screenStatus is ScreenState.Students) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { saveCheckStudent() },
                        enabled = true,
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colors.onSurface,
                            checkmarkColor = MaterialTheme.colors.surface
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                ) {

                    Text(
                        text = item, style = MaterialTheme.typography.h5.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                }
                if (screenStatus is ScreenState.Students) {

                    IconButton(onClick = {
                        deleteStudent()
                        coroutineScope.launch {
                            itemShown = false
                        }

                    }
                    ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.button_close_description)
                        )
                    }
                }
            }
        }
    }


}