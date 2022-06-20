package com.cryoggen.domain.repository

import com.cryoggen.domain.models.*

interface StudentControlRepository {
    suspend fun getPractices(): List<PracticeDomain>
    suspend fun getStudents(practiceId: String): List<StudentDomain>
    suspend  fun getStudentControlList(practiceId: String, taskId: String): List<StudentControlDomain>
    suspend fun getTasks(practiceId: String): List<TaskDomain>
    suspend fun getCheckedStudents(practiceId: String, taskId: String): List<CheckedStudentDomain>
    suspend fun insertStudents(students: List<StudentDomain>)
    suspend fun insertTasks(tasks: List<TaskDomain>)
    suspend fun insertPractice(practices: List<PracticeDomain>)
    suspend fun insertStudentControl(studentControlDomainList: List<StudentControlDomain>)
    suspend fun deleteStudent(studentId: String)
    suspend fun deleteTaskStudent(taskId: String, studentId: String)
    suspend fun deleteTask(taskId: String)
    suspend fun deletePractice(practiceId: String)

}