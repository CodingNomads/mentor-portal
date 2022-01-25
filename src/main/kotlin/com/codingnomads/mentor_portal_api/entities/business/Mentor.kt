package com.codingnomads.mentor_portal_api.entities.business

data class Mentor(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val roleCode: Int,
    val statusCode: Int,
    val email: String,
    val telephone: String
)