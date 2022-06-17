package com.cryoggen.data.source.local

import android.content.Context
import androidx.room.*
import com.cryoggen.data.source.models.local.*
import com.cryoggen.domain.models.CheckedStudentDomain
import com.cryoggen.domain.models.StudentControlDomain


@Dao
interface StudentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudents(students: List<StudentDatabaseModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<TaskDatabaseModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPractice(practices: List<PracticeDatabaseModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentControl(studentControlDatabaseModelList: List<StudentControlDatabaseModel>)

    @Query("select * from PracticeDatabaseModel")
    suspend fun getPractices(): List<PracticeDatabaseModel>

    @Query("select distinct TaskDatabaseModel.id , TaskDatabaseModel.name " +
            "FROM StudentControlDatabaseModel, TaskDatabaseModel" +
            " WHERE StudentControlDatabaseModel.taskId == TaskDatabaseModel.id and StudentControlDataBaseModel.practiceId = :practiceId")
    suspend fun getTasks(practiceId: String): List<TaskDatabaseModel>

    @Query("select distinct StudentControlDatabaseModel.id, StudentControlDatabaseModel.practiceId,StudentControlDatabaseModel.taskId,"+
            " StudentControlDatabaseModel.nameId, StudentDatabaseModel.name, StudentControlDatabaseModel.`check` " +
            "FROM StudentControlDatabaseModel, StudentDatabaseModel" +
            " WHERE StudentControlDatabaseModel.nameId == StudentDatabaseModel.id"+
            " and StudentControlDataBaseModel.practiceId = :practiceId and StudentControlDataBaseModel.taskId = :taskId" )
    suspend fun getCheckedStudents(practiceId: String, taskId: String): List<CheckedStudentDatabaseModel>

    @Query("select * FROM StudentControlDatabaseModel  where practiceId = :practiceId and taskId = :taskId")
    suspend fun getStudentControlList(practiceId: String, taskId: String): List<StudentControlDatabaseModel>

    @Query("delete from StudentControlDatabaseModel where id = :studentId")
    suspend fun deleteStudentControl(studentId: String)

    @Query("delete from StudentControlDatabaseModel where taskId= :taskId and nameId = :studentId")
    suspend fun deleteTaskStudent(taskId: String, studentId: String)

    @Query("delete from StudentDatabaseModel where id = :studentId")
    suspend fun deleteStudent(studentId: String)

    @Query("delete from TaskDatabaseModel where id = :taskId")
    suspend fun deleteTask(taskId: String)

    @Query("delete from StudentControlDatabaseModel where taskId = :taskId")
    suspend fun deleteTaskStudentControl(taskId: String)

    @Query("delete from PracticeDatabaseModel where id = :practiceId")
    suspend fun deletePractice(practiceId: String)

    @Query("delete from StudentControlDatabaseModel where practiceId = :practiceId")
    suspend fun deletePracticeStudentControl(practiceId: String)



}

@Database(
    entities = [StudentControlDatabaseModel::class, StudentDatabaseModel::class, PracticeDatabaseModel::class, TaskDatabaseModel::class],
    version = 1
)
abstract class StudentDatabase : RoomDatabase() {
    abstract val studentsDao: StudentsDao
}

private lateinit var INSTANCE: StudentDatabase

fun getDatabase(context: Context): StudentDatabase {
    synchronized(StudentDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                StudentDatabase::class.java,
                "students"
            ).build()
        }
    }
    return INSTANCE
}
