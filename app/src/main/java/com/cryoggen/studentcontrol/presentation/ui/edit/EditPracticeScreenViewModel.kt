package com.cryoggen.studentcontrol.presentation.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryoggen.domain.models.StudentDomain
import com.cryoggen.domain.usecase.InsertStudentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPracticeScreenViewModel @Inject constructor(
    private val insertStudentsUseCase: InsertStudentsUseCase
) : ViewModel() {

    fun insertStudents(practice: String, tasks: List<String>, studentNames: List<String>) {

        val listStudentDomain = mutableListOf<StudentDomain>()
        for (task in tasks) {
            for (name in studentNames) {
                listStudentDomain.add(StudentDomain(practice = practice, task = task, name = name))
            }
        }
        viewModelScope.launch {
            insertStudentsUseCase.execute(listStudentDomain)
        }
    }
}