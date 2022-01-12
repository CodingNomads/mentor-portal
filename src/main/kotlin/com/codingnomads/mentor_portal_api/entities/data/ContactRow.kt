package com.codingnomads.mentor_portal_api.entities.data

import com.codingnomads.mentor_portal_api.entities.business.User
import java.time.LocalDateTime

data class ContactRow(
    val id:Int,
    val userId: Int,
    val email: String,
    val telephone: String,
    val forumUsername: String,
    val slackUsername: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)