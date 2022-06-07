package com.cryoggen.data.source.local

import android.content.Context
import androidx.room.*
import com.cryoggen.data.source.models.local.StudentDatabaseModel

@Dao
interface StudentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudents(students: List<StudentDatabaseModel>)

    @Query("select distinct practice from StudentDatabaseModel")
    suspend fun getPractices(): List<String>

    //"distinct" - returns non-duplicate values
    @Query("select distinct task from StudentDatabaseModel where practice = :practice")
    suspend fun getTasks(practice: String): List<String>

    @Query("select * from StudentDatabaseModel  where practice = :practice and task = :task")
    suspend fun getStudents(practice: String, task: String): List<StudentDatabaseModel>

}

@Database(entities = [StudentDatabaseModel::class], version = 1)
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
