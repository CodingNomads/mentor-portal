package com.codingnomads.mentor_portal_api.entity.business

data class StudentDataRelation(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val roleCode: Int = 20,
    val statusCode: Int,
    val flag: Boolean,
    val bio: String,
    val location: String,
    val email: String,
    val telephone: String,
    val forumUsername: String,
    val slackUsername: String,
    val assignedMentors: List<MentorData>,
    val courseTrack: String? = null,
    val mentorshipOption: String? = null
)