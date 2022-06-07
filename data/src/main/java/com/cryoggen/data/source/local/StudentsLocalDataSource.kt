package com.cryoggen.data.source.local

import com.cryoggen.data.source.models.local.StudentDatabaseModel


interface StudentsLocalDataSource {
    suspend fun getPractices(): List<String>
    suspend fun getStudents(practice: String, task: String): List<StudentDatabaseModel>
    suspend fun getTasks(practice: String): List<String>
    suspend fun insertStudents(students: List<StudentDatabaseModel>)
}