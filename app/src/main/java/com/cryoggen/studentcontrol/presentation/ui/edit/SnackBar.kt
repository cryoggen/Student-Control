package com.cryoggen.studentcontrol.presentation.ui.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SnackBar(text: String, stopSnackBar: () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val snackbarCoroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()
    Scaffold(scaffoldState = scaffoldState) {
        snackbarCoroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(text)
            stopSnackBar()
        }
    }
}
