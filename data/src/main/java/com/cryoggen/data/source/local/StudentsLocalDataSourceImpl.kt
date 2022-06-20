package com.cryoggen.data.source.local

import com.cryoggen.data.source.models.local.*


class StudentsLocalDataSourceImpl(private val studentDatabase: StudentDatabase) :
    StudentsLocalDataSource {

    override suspend fun getPractices(): List<PracticeDatabaseModel> {
        return studentDatabase.studentsDao.getPractices()

    }

    override suspend fun getStudents(practiceId: String): List<StudentDatabaseModel> {
        return studentDatabase.studentsDao.getStudents(practiceId)
    }

    override suspend fun getTasks(practiceId:String): List<TaskDatabaseModel> {

        return studentDatabase.studentsDao.getTasks(practiceId = practiceId)
    }

    override suspend fun getCheckedStudents(practiceId:String, taskId:String): List<CheckedStudentDatabaseModel> {
        return studentDatabase.studentsDao.getCheckedStudents(practiceId = practiceId, taskId = taskId)
    }

    override suspend fun insertStudents(students: List<StudentDatabaseModel>) {
        studentDatabase.studentsDao.insertStudents(students)
    }

    override suspend fun insertTasks(tasks: List<TaskDatabaseModel>) {
        studentDatabase.studentsDao.insertTasks(tasks)
    }

    override suspend fun insertPractice(practices: List<PracticeDatabaseModel>) {
        studentDatabase.studentsDao.insertPractice(practices)
    }

    override suspend fun insertStudentControl(studentControlDatabaseModelList: List<StudentControlDatabaseModel>) {
        studentDatabase.studentsDao.insertStudentControl(studentControlDatabaseModelList)
    }


    override suspend fun getStudentControlList(practiceId: String, taskId: String): List<StudentControlDatabaseModel> {
        return studentDatabase.studentsDao.getStudentControlList(practiceId = practiceId, taskId = taskId)
    }


    override suspend fun deleteStudent(studentId: String) {
        studentDatabase.studentsDao.deleteStudent(studentId)
        studentDatabase.studentsDao.deleteStudentControl(studentId)
    }

    override suspend fun deleteTaskStudent(taskId: String, studentId: String) {
        studentDatabase.studentsDao.deleteTaskStudent(taskId = taskId, studentId = studentId)
    }

    override suspend fun deleteTask(taskId: String) {
        studentDatabase.studentsDao.deleteTask(taskId)
        studentDatabase.studentsDao.deleteTaskStudentControl(taskId)
    }

    override suspend fun deletePractice(practiceId: String) {
        studentDatabase.studentsDao.deletePractice(practiceId)
        studentDatabase.studentsDao.deletePracticeStudentControl(practiceId)
    }

}