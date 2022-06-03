package com.cryoggen.data.source.local

import com.cryoggen.data.source.models.local.StudentDatabaseModel


interface StudentsLocalDataSource {
    fun getPractices(): List<String>
    fun getStudents(practice: String, task: String): List<StudentDatabaseModel>
    fun getTasks(practice: String): List<String>
}