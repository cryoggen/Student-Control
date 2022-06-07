package com.cryoggen.data.repository

import android.util.Log
import com.cryoggen.data.source.local.StudentsLocalDataSource
import com.cryoggen.data.source.models.local.asDatabaseModel
import com.cryoggen.data.source.models.local.asDomainModel
import com.cryoggen.domain.models.StudentDomain
import com.cryoggen.domain.repository.StudentControlRepository

 class StudentControlRepositoryImpl(
    private val StudentControlLocalDataSource: StudentsLocalDataSource
) : StudentControlRepository {
     override suspend fun getPractices(): List<String> {
        return StudentControlLocalDataSource.getPractices()
     }

     override suspend fun getTasks(practice: String): List<String> {
         return StudentControlLocalDataSource.getTasks(practice = practice)
     }

     override suspend fun insertStudents(students: List<StudentDomain>) {
         val listStudentsDataBaseModel = students.asDatabaseModel()
         StudentControlLocalDataSource.insertStudents(listStudentsDataBaseModel)
     }

     override suspend fun getStudents(practice: String, task: String): List<StudentDomain> {
         val listStudentsDataBaseModel = StudentControlLocalDataSource.getStudents(practice = practice, task = task)
         return listStudentsDataBaseModel.asDomainModel()
     }

 }