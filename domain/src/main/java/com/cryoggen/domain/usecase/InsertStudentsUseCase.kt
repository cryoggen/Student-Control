package com.cryoggen.domain.usecase

import com.cryoggen.domain.models.StudentDomain
import com.cryoggen.domain.repository.StudentControlRepository

class InsertStudentsUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(students: List<StudentDomain>) {
        return studentControlRepository.insertStudents(students = students)
    }
}