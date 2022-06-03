package com.cryoggen.domain.repository

import com.cryoggen.domain.models.StudentDomain

interface StudentControlRepository {
    fun getPractices(): List<String>
    fun getStudents(practice: String, task: String): List<StudentDomain>
    fun getTasks(practice: String): List<String>
}