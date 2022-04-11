package com.codingnomads.mentor_portal_api.entity.business

data class  MentorData(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val roleCode: Int = 10,
    val statusCode: Int,
    val flag: Boolean,
    val bio: String,
    val location: String,
    val email: String,
    val telephone: String,
    val forumUsername: String,
    val slackUsername: String,
//    val maxStudentOption: String,
//    val maxStudentValue: List<String>,
//    val proficiencyOption: String,
//    val proficienciesValue: List<String>
)