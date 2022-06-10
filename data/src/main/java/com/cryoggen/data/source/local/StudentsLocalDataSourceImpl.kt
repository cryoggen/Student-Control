package com.cryoggen.data.source.local

import android.util.Log
import com.cryoggen.data.source.models.local.StudentDatabaseModel
import com.cryoggen.data.source.models.local.asDomainModel
import java.lang.Exception

class StudentsLocalDataSourceImpl(private val studentDatabase: StudentDatabase) :
    StudentsLocalDataSource {
    override suspend fun getPractices(): List<String> {
        var practices = listOf<String>()
        try {
            practices = studentDatabase.studentsDao.getPractices()
        } catch (e: Exception) {

        }

        return practices
    }

    override suspend fun getTasks(practice: String): List<String> {
        return studentDatabase.studentsDao.getTasks(practice = practice)
    }


    override suspend fun getStudents(practice: String, task: String): List<StudentDatabaseModel> {
        return studentDatabase.studentsDao.getStudents(practice = practice, task = task)
    }

    override suspend fun insertStudents(students: List<StudentDatabaseModel>) {
        studentDatabase.studentsDao.insertStudents(students)
    }

    override suspend fun deleteStudents(students: List<StudentDatabaseModel>) {
        studentDatabase.studentsDao.deleteStudents(students)
    }

}