package com.cryoggen.studentcontrol.presentation.ui.edit

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SnackBar(text: String, stopSnackBar: () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val snackbarCoroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        snackbarCoroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(text)
            stopSnackBar()
        }
    }


}
