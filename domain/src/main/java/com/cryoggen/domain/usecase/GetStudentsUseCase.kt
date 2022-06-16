package com.cryoggen.domain.usecase

import com.cryoggen.domain.models.CheckedStudentDomain
import com.cryoggen.domain.repository.StudentControlRepository

class GetStudentsUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(practiceId:String, taskId:String): List<CheckedStudentDomain> {
        return studentControlRepository.getStudents(practiceId = practiceId, taskId = taskId)
    }
}