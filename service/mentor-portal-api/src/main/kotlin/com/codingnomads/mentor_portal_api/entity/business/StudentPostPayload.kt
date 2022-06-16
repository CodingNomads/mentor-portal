package com.codingnomads.mentor_portal_api.entity.business

data class StudentPostPayload(
    /**
     * user fields
     */
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val bio: String? = "UPDATE STUDENT BIO",
    val timezoneOffset: String,
    /**
     * contact fields
     */
    val email: String,
    val telephone: String,
    val location: String,
    val forumUsername: String? = "",
    val slackUsername: String? = "",
    /**
     * config
     */
    val courseTrack: String,
)