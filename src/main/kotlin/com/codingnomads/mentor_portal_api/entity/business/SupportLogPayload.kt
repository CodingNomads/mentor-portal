package com.codingnomads.mentor_portal_api.entity.business

import java.sql.Timestamp

data class SupportLogPayload(
    val mentorId: Int,
    val studentId: Int,
    val type: String,
    val flag: Boolean,
    val duration: Int,
    val log: String,
    val logDate: Timestamp,
)