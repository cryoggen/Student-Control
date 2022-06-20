package com.cryoggen.studentcontrol.presentation.ui.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SnackBar(text: String, stopSnackBar: () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val snackbarCoroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(72.dp)
                .align(alignment = Alignment.BottomEnd)
        ) {
            Scaffold(scaffoldState = scaffoldState) {
                snackbarCoroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(text)
                    stopSnackBar()
                }
            }
        }
    }


}
