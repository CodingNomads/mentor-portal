package com.codingnomads.mentor_portal_api.entity.business

data class UserSecurityRow(
    // values from database
    val userId: Long,
    val email: String,
    val passwordHash: String,
    val isAdmin: Boolean,
    val statusCode: Long,
    )
