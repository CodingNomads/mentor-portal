package com.codingnomads.mentor_portal_api.entities.data

data class UserModel (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val roleCode: Int,
    val statusCode: Int,
    val timezoneOffset: String
        )