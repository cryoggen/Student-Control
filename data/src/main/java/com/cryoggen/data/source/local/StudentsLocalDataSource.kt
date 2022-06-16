package com.cryoggen.data.source.local

import com.cryoggen.data.source.models.local.*


interface StudentsLocalDataSource {
    suspend fun getPractices(): List<PracticeDatabaseModel>
    suspend fun getStudentControlList(practiceId: String, taskId: String): List<StudentControlDatabaseModel>
    suspend fun getTasks(practiceId: String): List<TaskDatabaseModel>
    suspend fun getStudents(practiceId: String, taskId: String): List<CheckedStudentDatabaseModel>
    suspend fun insertStudents(students: List<StudentDatabaseModel>)
    suspend fun insertTasks(tasks: List<TaskDatabaseModel>)
    suspend fun insertPractice(practices: List<PracticeDatabaseModel>)
    suspend fun insertStudentControl(studentControlDatabaseModelList: List<StudentControlDatabaseModel>)
    suspend fun deleteStudent(studentId: String)
    suspend fun deleteTask(taskId: String)
    suspend fun deletePractice(practiceId: String)
}