package com.codingnomads.mentor_portal_api.entities.business

import com.codingnomads.mentor_portal_api.entities.data.MentorRow

data class Mentor(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val roleCode: Int,
    val statusCode: Int,
    val timezoneOffset: String
) {
    companion object {
        fun fromEntity(mentorRow: MentorRow) = Mentor(
            id = mentorRow.id,
            firstName = mentorRow.firstName,
            lastName = mentorRow.lastName,
            roleCode = mentorRow.roleCode,
            statusCode = mentorRow.statusCode,
            timezoneOffset = mentorRow.timezoneOffset
        )
    }
}