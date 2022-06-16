package com.cryoggen.domain.usecase

import com.cryoggen.domain.models.StudentControlDomain
import com.cryoggen.domain.repository.StudentControlRepository

class DeleteStudentUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(studentId: String) {
        return studentControlRepository.deleteStudent(studentId = studentId)
    }
}
