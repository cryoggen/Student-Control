package com.cryoggen.domain.usecase

import com.cryoggen.domain.models.PracticeDomain
import com.cryoggen.domain.repository.StudentControlRepository

class GetPracticesUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(): List<PracticeDomain> {
        return studentControlRepository.getPractices()
    }
}