package com.codingnomads.mentor_portal_api.entities.business

import com.codingnomads.mentor_portal_api.entities.data.UserRow

data class Student(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val roleCode: Int,
    val statusCode: Int,
)