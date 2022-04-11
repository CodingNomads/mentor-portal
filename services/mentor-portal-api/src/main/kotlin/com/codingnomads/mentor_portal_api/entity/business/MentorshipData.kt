package com.codingnomads.mentor_portal_api.entity.business

data class MentorshipData(
    val id: Int,
    val mentorId: Int,
    val studentId: Int,
    val statusCode: Int
)