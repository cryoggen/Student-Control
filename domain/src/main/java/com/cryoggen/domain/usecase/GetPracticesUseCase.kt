package com.cryoggen.domain.usecase

import com.cryoggen.domain.repository.StudentControlRepository

class GetPracticesUseCase(private val studentControlRepository: StudentControlRepository) {
      fun execute(): List<String> {
        return studentControlRepository.getPractices()
    }
}