package com.codingnomads.mentor_portal_api.entity.data

import java.time.LocalDateTime

data class RoleRow(
    val id: Int,
    val code: Int,
    val name: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime

)