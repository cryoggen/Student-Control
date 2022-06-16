package com.cryoggen.domain.usecase

import com.cryoggen.domain.repository.StudentControlRepository

class DeletePracticeUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(practiceId: String) {
        return studentControlRepository.deletePractice(practiceId = practiceId)
    }
}