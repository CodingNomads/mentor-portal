package com.codingnomads.mentor_portal_api.entity.data

data class SecurityRow(
    val id: Int? = null,
    val userId: Int,
    val username: String,
    val passwordHash: String,
    val isAdmin: Boolean,
)