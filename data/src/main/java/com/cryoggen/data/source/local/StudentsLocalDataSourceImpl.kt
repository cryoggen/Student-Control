package com.cryoggen.data.source.local

import com.cryoggen.data.source.models.local.StudentDatabaseModel

class StudentsLocalDataSourceImpl(private val studentDatabase: StudentDatabase) : StudentsLocalDataSource {
    override fun getPractices(): List<String> {
        return studentDatabase.studentsDao.getPractices()
    }

    override fun getTasks(practice: String): List<String> {
        return studentDatabase.studentsDao.getTasks(practice = practice )
    }

    override fun getStudents(practice: String, task: String): List<StudentDatabaseModel> {
        return studentDatabase.studentsDao.getStudents(practice = practice,task = task )
    }



}