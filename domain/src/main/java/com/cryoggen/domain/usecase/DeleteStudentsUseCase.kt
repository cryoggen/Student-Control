package com.cryoggen.domain.usecase

import com.cryoggen.domain.models.StudentDomain
import com.cryoggen.domain.repository.StudentControlRepository

class DeleteStudentsUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(students: List<StudentDomain>) {
        return studentControlRepository.deleteStudents(students = students)
    }
}
