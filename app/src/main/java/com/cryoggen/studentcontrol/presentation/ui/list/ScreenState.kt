package com.cryoggen.studentcontrol.presentation.ui.list

import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.cryoggen.domain.models.StudentDomain

sealed class ScreenState {

    data class Practices(
        var listPractices: List<String> = listOf(),
        val navBar: NavBar,
        var itemListOnClickItem: (String) -> Unit,
        var floatingButtonOnClick: () -> Unit
    ) : ScreenState()

    data class Tasks(
        val practice: String,
        var listTasks: List<String> = listOf(),
        var listPractices: List<String> = listOf(),
        val navBar: NavBar,
        var itemListOnClickItem: (String, String) -> Unit,
    ) : ScreenState()

    data class Students(
        var listPractices: List<String> = listOf(),
        val navBar: NavBar,
        val practice: String,
        val task: String,
        var listStudents: List<StudentDomain> = listOf(),
        var saveCheckStudent: (StudentDomain) -> Unit = {},
        var deleteStudent: (StudentDomain) -> Unit = {}
    ) : ScreenState()

    data class Edit(
        val navBar: NavBar,
        var insertStudent: () -> Unit = {}
    ): ScreenState()

}

data class NavBar(
    val iconLeftOnClick: () -> Unit = {},
    var iconRightOnClick: () -> Unit = {},
    val iconLeft: ImageVector = Icons.Filled.House,
    val iconRight: ImageVector = Icons.Filled.House,
    val titleText: String = "",
)