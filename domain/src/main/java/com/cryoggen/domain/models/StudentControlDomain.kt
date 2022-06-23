package com.cryoggen.domain.models

import java.util.*


data class PracticeDomain(
    val id:String = UUID.randomUUID().toString(),
    var name: String,
    val date:String = Date().toString()
)

data class StudentControlDomain(
    val id:String = UUID.randomUUID().toString(),
    val practiceId: String,
    val taskId: String,
    val nameId: String,
    var check: Boolean = false
)

data class CheckedStudentDomain(
    val id:String = UUID.randomUUID().toString(),
    val practiceId: String,
    val taskId: String,
    var nameId: String,
    val name: String,
    var check:Boolean
)

data class TaskDomain(
    var id:String = UUID.randomUUID().toString(),
    var name: String,
    val date:String = Date().toString()
)

data class StudentDomain(
    var id:String = UUID.randomUUID().toString(),
    var name: String
)





