package com.cryoggen.domain.usecase

import com.cryoggen.domain.models.StudentControlDomain
import com.cryoggen.domain.repository.StudentControlRepository

class InsertStudentControlUseCase(private val studentControlRepository: StudentControlRepository) {
    suspend fun execute(studentControlDomainList: List<StudentControlDomain>) {
        return studentControlRepository.insertStudentControl(studentControlDomainList = studentControlDomainList)
    }
}