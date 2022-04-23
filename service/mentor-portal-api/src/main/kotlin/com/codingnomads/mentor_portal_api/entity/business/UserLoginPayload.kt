package com.codingnomads.mentor_portal_api.entity.business

data class UserLoginPayload(
    val email: String,
    val password: String
)
