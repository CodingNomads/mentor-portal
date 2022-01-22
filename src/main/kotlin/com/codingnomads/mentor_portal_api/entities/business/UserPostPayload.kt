package com.codingnomads.mentor_portal_api.entities.business

import com.codingnomads.mentor_portal_api.entities.data.UserRow
import com.codingnomads.mentor_portal_api.entities.business.User

data class UserPostPayload (
    /**
     * user fields
     */
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val roleCode: Int,
    val statusCode: Int,
    val timezoneOffset: Int,
    /**
     * contact fields
     */
    val email: String,
    val telephone: String,
    val forumUsername: String,
    val slackUsername: String,
    /**
     * security fields
     */
//    val userId: Int,
    val username: String,
    val passwordHash: String,
    val isAdmin: Boolean,
)