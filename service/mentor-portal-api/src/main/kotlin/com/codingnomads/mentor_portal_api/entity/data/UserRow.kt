package com.codingnomads.mentor_portal_api.entity.data

import com.codingnomads.mentor_portal_api.entity.business.User

data class UserRow (
    val id: Int? = null,
    val email: String,
    val firstName: String,
    val lastName: String,
    val roleCode: Int,
    val statusCode: Int,
    val flag: Boolean,
    val bio: String,
    val timezoneOffset: String
    ) {
    companion object {
        fun fromEntity(user: User) = UserRow(
            id = user.id,
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            roleCode = user.roleCode,
            statusCode = user.statusCode,
            flag = user.flag,
            bio = user.bio,
            timezoneOffset = user.timezoneOffset
        )
    }
}