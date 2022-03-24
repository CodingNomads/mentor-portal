package com.codingnomads.mentor_portal_api.entity.business

data class AssignMentorPayload(
    val mentorId: Int,
    val studentId: Int,
)