package com.codingnomads.mentor_portal_api.entity.business

data class Student(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val roleCode: Int,
    val statusCode: Int,
    val email: String,
    val telephone: String
)