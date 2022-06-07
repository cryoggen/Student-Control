package com.cryoggen.domain.models

import java.util.*

data class StudentDomain(
    val id:String = UUID.randomUUID().toString(),
    val practice: String,
    val task: String,
    val name: String,
    var check: Boolean = false
)

