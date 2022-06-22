package com.cryoggen.studentcontrol.presentation.ui.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.House
import androidx.compose.ui.graphics.vector.ImageVector
import com.cryoggen.domain.models.CheckedStudentDomain
import com.cryoggen.domain.models.PracticeDomain
import com.cryoggen.domain.models.StudentControlDomain
import com.cryoggen.domain.models.TaskDomain

sealed class ScreenState {

    data class Practices(
        var listPractices: List<PracticeDomain> = listOf(),
        val navBar: NavBar,
        var itemListOnClickItem: (String, String) -> Unit,
        var floatingButtonOnClick: () -> Unit
    ) : ScreenState()

    data class Tasks(
        val practiceId: String,
        val practiceName: String,
        var listTasks: List<TaskDomain> = listOf(),
        val navBar: NavBar,
        var itemListOnClickItem: (String, String, String, String) -> Unit,
        var onEditPracticePressed: () -> Unit,
    ) : ScreenState()

    data class Students(
        val navBar: NavBar,
        val practiceId: String,
        val practiceName: String,
        val taskId: String,
        val taskName: String,
        var checkedStudentDomainList: List<CheckedStudentDomain> = listOf(),
        var saveCheckStudent: (StudentControlDomain) -> Unit = {},
        var deleteTaskStudent: (String, String) -> Unit = { _, _ -> },


        ) : ScreenState()

    data class NewPractice(
        val navBar: NavBar,
        var insertStudents: () -> Unit = {},
    ) : ScreenState()

    data class EditPractice(
        val practiceId: String,
        val practiceName: String,
        val navBar: NavBar,
        var insertStudent: () -> Unit = {},
    ) : ScreenState()
}

data class NavBar(
    var onOpenMenuPressed: () -> Unit = {},
    val iconLeftOnClick: () -> Unit = {},
    var iconRightOnClick: () -> Unit = {},
    val iconLeft: ImageVector = Icons.Filled.House,
    val iconRight: ImageVector = Icons.Filled.House,
    val titleText: String = "",
)

