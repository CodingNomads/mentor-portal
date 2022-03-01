package com.codingnomads.mentor_portal_api.entity.business

import java.sql.Timestamp

data class SupportLog (
    val id: Int? = null,
    val mentorFirstName: String,
    val mentorLastName: String,
    val mentorId: Int,
    val studentId: Int,
    val log: String,
    val createdAt: Timestamp,
    val updatedAt: Timestamp
)