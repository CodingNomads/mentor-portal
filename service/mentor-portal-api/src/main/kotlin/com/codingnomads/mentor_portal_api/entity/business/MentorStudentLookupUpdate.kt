package com.codingnomads.mentor_portal_api.entity.business

data class MentorStudentLookupUpdate(
    val id: Int,
    val mentorId: Int,
    val studentId: Int,
    val description: String
)