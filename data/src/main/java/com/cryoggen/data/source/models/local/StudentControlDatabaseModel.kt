package com.cryoggen.data.source.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cryoggen.domain.models.*
import java.util.*

@Entity
data class StudentControlDatabaseModel constructor(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val practiceId: String,
    val taskId: String,
    val nameId: String,
    val check: Boolean
)

data class CheckedStudentDatabaseModel(
    val id:String,
    val practiceId: String,
    val taskId: String,
    val nameId: String,
    val name: String,
    val check:Boolean
)


@Entity
data class StudentDatabaseModel(
    @PrimaryKey
    val id:String,
    val name: String
)

@Entity
data class PracticeDatabaseModel(
    @PrimaryKey
    val id:String,
    val name: String
)

@Entity
data class TaskDatabaseModel(
    @PrimaryKey
    val id:String,
    val name: String
)


fun List<StudentControlDatabaseModel>.asDomainModel(): List<StudentControlDomain> {
    return map {
        StudentControlDomain(
            id = it.id,
            practiceId = it.practiceId,
            taskId = it.taskId,
            nameId = it.nameId,
            check = it.check
        )
    }
}

fun List<StudentControlDomain>.asDatabaseModel(): List<StudentControlDatabaseModel> {
    return map {
        StudentControlDatabaseModel(
            id = it.id,
            practiceId = it.practiceId,
            taskId = it.taskId,
            nameId = it.nameId,
            check = it.check
        )
    }
}



@JvmName("asDatabaseModelStudentNamesDomain")
fun List<StudentDomain>.asDatabaseModel(): List<StudentDatabaseModel> {
    return map {
        StudentDatabaseModel(
            id = it.id,
            name = it.name
        )
    }
}

@JvmName("asDatabaseModelPracticeNamesDomain")
fun List<PracticeDomain>.asDatabaseModel(): List<PracticeDatabaseModel> {
    return map {
        PracticeDatabaseModel(
            id = it.id,
            name = it.name
        )
    }
}

@JvmName("asDatabaseModelTaskNamesDomain")
fun List<TaskDomain>.asDatabaseModel(): List<TaskDatabaseModel> {
    return map {
        TaskDatabaseModel(
            id = it.id,
            name = it.name
        )
    }
}


@JvmName("asDatabaseModelStudentNamesDatabaseModel")
fun List<StudentDatabaseModel>.asDomainModel(): List<StudentDomain> {
    return map {
        StudentDomain(
            id = it.id,
            name = it.name
        )
    }
}


@JvmName("asDatabaseModelPracticeNamesDatabaseMode")
fun List<PracticeDatabaseModel>.asDomainModel(): List<PracticeDomain> {
    return map {
        PracticeDomain(
            id = it.id,
            name = it.name
        )
    }
}


@JvmName("asDatabaseModelTaskNamesDatabaseModel")
fun List<TaskDatabaseModel>.asDomainModel(): List<TaskDomain> {
    return map {
        TaskDomain(
            id = it.id,
            name = it.name
        )
    }
}


@JvmName("asDomainModelCheckedStudentDatabaseModel")
fun List<CheckedStudentDatabaseModel>.asDomainModel(): List<CheckedStudentDomain> {
    return map {
        CheckedStudentDomain(
            id = it.id,
            practiceId = it.practiceId,
            taskId = it.taskId,
            nameId = it.nameId,
            name = it.name,
            check = it.check
        )
    }
}