package com.cryoggen.data.repository

import com.cryoggen.data.source.local.StudentsLocalDataSource
import com.cryoggen.data.source.models.local.asDatabaseModel
import com.cryoggen.data.source.models.local.asDomainModel
import com.cryoggen.domain.models.*
import com.cryoggen.domain.repository.StudentControlRepository

 class StudentControlRepositoryImpl(
    private val studentsLocalDataSource: StudentsLocalDataSource
) : StudentControlRepository {
     override suspend fun getPractices(): List<PracticeDomain> {
        return studentsLocalDataSource.getPractices().asDomainModel()
     }

     override suspend fun getTasks(practiceId: String): List<TaskDomain> {
         return studentsLocalDataSource.getTasks(practiceId).asDomainModel()
     }

     override suspend fun getStudents(practiceId: String, taskId: String): List<CheckedStudentDomain> {
         return studentsLocalDataSource.getStudents(practiceId = practiceId, taskId = taskId).asDomainModel()
     }

     override suspend fun insertStudents(students: List<StudentDomain>) {
         studentsLocalDataSource.insertStudents(students.asDatabaseModel())
     }

     override suspend fun insertTasks(tasks: List<TaskDomain>) {
         studentsLocalDataSource.insertTasks(tasks.asDatabaseModel())
     }

     override suspend fun insertPractice(practices: List<PracticeDomain>) {
         studentsLocalDataSource.insertPractice(practices.asDatabaseModel())
     }

     override suspend fun insertStudentControl(studentControlDomainList: List<StudentControlDomain>) {
         studentsLocalDataSource.insertStudentControl(studentControlDomainList.asDatabaseModel())
     }

     override suspend fun deleteStudent(studentId: String) {
         studentsLocalDataSource.deleteStudent(studentId)
     }

     override suspend fun deleteTaskStudent(taskId: String, studentId: String) {
      return studentsLocalDataSource.deleteTaskStudent(taskId = taskId,studentId = studentId)
     }

     override suspend fun deleteTask(taskId: String) {
         studentsLocalDataSource.deleteTask(taskId)
     }

     override suspend fun deletePractice(practiceId: String) {
         studentsLocalDataSource.deletePractice(practiceId)
     }


     override suspend fun getStudentControlList(practiceId: String, taskId: String): List<StudentControlDomain> {
         val listStudentsDataBaseModel = studentsLocalDataSource.getStudentControlList(practiceId = practiceId, taskId = taskId)
         return listStudentsDataBaseModel.asDomainModel()
     }

 }