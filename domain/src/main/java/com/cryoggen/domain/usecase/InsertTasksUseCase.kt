package com.cryoggen.domain.usecase

import com.cryoggen.domain.models.TaskDomain
import com.cryoggen.domain.repository.StudentControlRepository

class InsertTasksUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(tasks: List<TaskDomain>) {
        return studentControlRepository.insertTasks(tasks = tasks)
    }
}