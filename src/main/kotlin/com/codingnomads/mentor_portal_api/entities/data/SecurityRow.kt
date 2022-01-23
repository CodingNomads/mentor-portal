package com.codingnomads.mentor_portal_api.entities.data

data class SecurityRow(
    val id: Int,
    val userId: Int,
    val userName: String,
    val passwordHash: String,
    val isAdmin: Boolean,
)