package com.cryoggen.data.source.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cryoggen.domain.models.StudentDomain

@Entity
data class StudentDatabaseModel constructor(
    @PrimaryKey
    val id: String,
    val practice: String,
    val task: String,
    val name: String,
    val check: Boolean
)

fun List<StudentDatabaseModel>.asDomainModel(): List<StudentDomain> {
    return map {
        StudentDomain(
            id = it.id,
            practice = it.practice,
            task = it.task,
            name = it.name,
            check = it.check
        )
    }
}

fun List<StudentDomain>.asDatabaseModel(): List<StudentDatabaseModel> {
    return map {
        StudentDatabaseModel(
            id = it.id,
            practice = it.practice,
            task = it.task,
            name = it.name,
            check = it.check
        )
    }
}