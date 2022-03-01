package com.codingnomads.mentor_portal_api.entity.business

data class Student(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val roleCode: Int = 20,
    val statusCode: Int,
    val email: String,
    val telephone: String,
    val forumUsername: String,
    val slackUsername: String,
    val assignedMentors: List<MentorData>? = null,
    val supportLog: List<SupportLog>? = null
)