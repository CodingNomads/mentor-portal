package com.codingnomads.mentor_portal_api.entities.data

import com.codingnomads.mentor_portal_api.entities.business.Mentor

data class MentorRow (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val roleCode: Int,
    val statusCode: Int,
    val timezoneOffset: String
) {
    companion object {
        fun fromEntity(mentor: Mentor) = MentorRow(
            id = mentor.id,
            firstName = mentor.firstName,
            lastName = mentor.lastName,
            roleCode = mentor.roleCode,
            statusCode = mentor.statusCode,
            timezoneOffset = mentor.timezoneOffset
        )
    }
}