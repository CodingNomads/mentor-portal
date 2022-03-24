package com.codingnomads.mentor_portal_api.entity.business

import java.sql.Date
import java.sql.Time
import java.sql.Timestamp

data class SupportLog (
    val id: Int? = null,
    val mentorFirstName: String,
    val mentorLastName: String,
    val mentorId: Int,
    val studentId: Int,
    val type: String,
    val duration: Int,
    val log: String,
    val logDate: Timestamp,
    val createdAt: Timestamp,
    val updatedAt: Timestamp
)