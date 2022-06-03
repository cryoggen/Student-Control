package com.cryoggen.data.repository

import com.cryoggen.data.source.local.StudentsLocalDataSource
import com.cryoggen.data.source.models.local.asDomainModel
import com.cryoggen.domain.models.StudentDomain
import com.cryoggen.domain.repository.StudentControlRepository

 class StudentControlRepositoryImpl(
    private val StudentControlLocalDataSource: StudentsLocalDataSource
) : StudentControlRepository {
     override fun getPractices(): List<String> {
        return StudentControlLocalDataSource.getPractices()
     }

     override fun getTasks(practice: String): List<String> {
         return StudentControlLocalDataSource.getTasks(practice = practice)
     }

     override fun getStudents(practice: String, task: String): List<StudentDomain> {
         return StudentControlLocalDataSource.getStudents(practice = practice, task = task).asDomainModel()
     }

 }