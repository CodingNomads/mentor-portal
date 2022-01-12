package com.codingnomads.mentor_portal_api.entities.data

import java.time.LocalDateTime

data class SecurityRow(
    val id: Int,
    val userId: Int,
    val userName: String,
    val passwordHash: String,
    val isAdmin: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)