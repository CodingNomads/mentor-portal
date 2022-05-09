package com.codingnomads.mentor_portal_api.entity.business

import com.codingnomads.mentor_portal_api.entity.data.UserRow
import com.fasterxml.jackson.annotation.JsonIgnore

data class User(
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
        fun fromEntity(userRow: UserRow) = User(
            id = userRow.id,
            email= userRow.email,
            firstName = userRow.firstName,
            lastName = userRow.lastName,
            roleCode = userRow.roleCode,
            statusCode = userRow.statusCode,
            flag = userRow.flag,
            bio = userRow.bio,
            timezoneOffset = userRow.timezoneOffset
        )
    }
}
