package com.codingnomads.mentor_portal_api.entity.business

data class MentorPostPayload (
    /**
     * user fields
     */
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val bio: String?="MENTOR IS TO UPDATE THEIR BIO",
    val timezoneOffset: String,
    /**
     * contact fields
     */
    val email: String,
    val telephone: String,
    val location: String,
    val forumUsername: String?="",
    val slackUsername: String?="",
    /**
     * config
     */
    val maxStudents: Int?=1,
    val proficiencies: List<String>,
)