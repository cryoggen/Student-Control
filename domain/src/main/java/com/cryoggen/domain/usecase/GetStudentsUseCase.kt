package com.cryoggen.domain.usecase

import com.cryoggen.domain.models.StudentDomain
import com.cryoggen.domain.repository.StudentControlRepository

class GetStudentsUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(practiceId:String): List<StudentDomain> {
        return studentControlRepository.getStudents(practiceId)
    }
}