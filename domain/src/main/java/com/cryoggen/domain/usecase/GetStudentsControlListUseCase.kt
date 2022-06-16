package com.cryoggen.domain.usecase

import com.cryoggen.domain.models.StudentControlDomain
import com.cryoggen.domain.repository.StudentControlRepository


class GetStudentsControlListUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(practiceId: String, taskId: String): List<StudentControlDomain> {
        return studentControlRepository.getStudentControlList(practiceId = practiceId, taskId = taskId)
    }
}