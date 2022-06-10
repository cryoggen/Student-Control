package com.cryoggen.domain.repository

import com.cryoggen.domain.models.StudentDomain

interface StudentControlRepository {
    suspend fun getPractices(): List<String>
    suspend  fun getStudents(practice: String, task: String): List<StudentDomain>
    suspend fun getTasks(practice: String): List<String>
    suspend fun insertStudents(students: List<StudentDomain>)
    suspend fun deleteStudents(students: List<StudentDomain>)
}