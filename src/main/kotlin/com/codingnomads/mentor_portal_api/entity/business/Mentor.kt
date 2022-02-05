package com.codingnomads.mentor_portal_api.entity.business

data class Mentor(
    val id: Int? = null,
    val firstName: String? = "",
    val lastName: String? = "",
    val roleCode: Int? = 0,
    val statusCode: Int? = 0,
    val email: String? = "",
    val telephone: String? = ""
)