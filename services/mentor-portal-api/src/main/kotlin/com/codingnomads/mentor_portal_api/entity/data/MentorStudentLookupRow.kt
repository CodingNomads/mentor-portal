package com.codingnomads.mentor_portal_api.entity.data

data class MentorStudentLookupRow(
    val id: Int? = null,
    val mentorId: Int,
    val studentId: Int,
    val statusCode: Int = 100
)