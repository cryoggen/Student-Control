package com.cryoggen.domain.usecase

import com.cryoggen.domain.repository.StudentControlRepository

class DeleteTaskStudentUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(taskId: String, studentId: String){
        return studentControlRepository.deleteTaskStudent(taskId = taskId, studentId = studentId)
    }
}
