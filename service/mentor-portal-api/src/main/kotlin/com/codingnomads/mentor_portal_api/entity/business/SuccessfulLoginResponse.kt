package com.codingnomads.mentor_portal_api.entity.business

data class SuccessfulLoginResponse(
    val token: String,
    val role: String,
    val isAdmin: Boolean
)
