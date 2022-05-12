package com.codingnomads.mentor_portal_api.entity.business

data class ResetPasswordPayload(
    val email: String,
    val currentPassword: String,
    val newPassword: String
)
