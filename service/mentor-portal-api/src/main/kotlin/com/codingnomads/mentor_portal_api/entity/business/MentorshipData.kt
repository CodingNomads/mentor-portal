package com.codingnomads.mentor_portal_api.entity.business

import java.sql.Date

data class MentorshipData(
    val id: Int,
    val mentorId: Int,
    val studentId: Int,
    val statusCode: Int,
    val startDate: Date? = null,
    val endDate: Date? = null
)