package com.codingnomads.mentor_portal_api.entity.business

data class SupportLogFlagPayload(
    val studentId: Int,
    val supportLogId: Int,
    val flag: Boolean,
    val log: String
)