package com.codingnomads.mentor_portal_api.entities.business

import com.codingnomads.mentor_portal_api.entities.data.UserRow

data class User(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val roleCode: Int,
    val statusCode: Int,
    val timezoneOffset: String
) {
    companion object {
        fun fromEntity(userRow: UserRow) = User(
            id = userRow.id,
            firstName = userRow.firstName,
            lastName = userRow.lastName,
            roleCode = userRow.roleCode,
            statusCode = userRow.statusCode,
            timezoneOffset = userRow.timezoneOffset
        )
    }
}
