package com.cryoggen.domain.usecase

import com.cryoggen.domain.models.PracticeDomain
import com.cryoggen.domain.models.TaskDomain
import com.cryoggen.domain.repository.StudentControlRepository

class InsertPracticeUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(practices: List<PracticeDomain>) {
        return studentControlRepository.insertPractice(practices = practices)
    }
}