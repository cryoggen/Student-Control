package com.cryoggen.studentcontrol.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.cryoggen.domain.usecase.GetPracticesUseCase
import com.cryoggen.domain.usecase.GetStudentsUseCase
import com.cryoggen.domain.usecase.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StudentControlViewModel @Inject constructor (
    private val getPracticesUseCase: GetPracticesUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel()
{
fun Test(){}

}


