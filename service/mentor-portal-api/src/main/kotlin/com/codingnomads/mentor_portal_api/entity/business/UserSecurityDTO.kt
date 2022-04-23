package com.codingnomads.mentor_portal_api.entity.business

data class UserSecurityDTO(
    var userId: Long,
    var email: String,
    var passwordHash: String,
    var statusCode: Long,
    var token: String,
    var role: String,
    var isAdmin: Boolean
    )
