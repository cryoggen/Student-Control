package com.cryoggen.domain.usecase

import com.cryoggen.domain.repository.StudentControlRepository

class GetTasksUseCase(private val studentControlRepository: StudentControlRepository) {
    fun execute(practice: String): List<String> {
        return studentControlRepository.getTasks(practice = practice)
    }
}