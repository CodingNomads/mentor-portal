package com.codingnomads.mentor_portal_api.entity.business

data class UserFlagPayload(
    val flag: Boolean,
    val userId: Int
)