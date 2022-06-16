package com.cryoggen.domain.usecase

import com.cryoggen.domain.models.TaskDomain
import com.cryoggen.domain.repository.StudentControlRepository

class GetTasksUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(practiceId:String): List<TaskDomain> {
        return studentControlRepository.getTasks(practiceId = practiceId)
    }
}