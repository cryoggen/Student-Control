package com.cryoggen.domain.usecase

import com.cryoggen.domain.repository.StudentControlRepository

class DeleteTaskUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(taskId: String) {
        return studentControlRepository.deleteTask(taskId = taskId)
    }
}
