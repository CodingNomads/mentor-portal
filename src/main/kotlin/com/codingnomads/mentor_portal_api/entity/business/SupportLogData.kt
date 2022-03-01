package com.codingnomads.mentor_portal_api.entity.business

import com.codingnomads.mentor_portal_api.entity.data.SupportLogRow
import java.sql.Timestamp

data class SupportLogData (
    val id: Int? = null,
    val mentorId: Int,
    val studentId: Int,
    val log: String,
    val createdAt: Timestamp,
    val updatedAt: Timestamp
    ) {
    companion object {
        fun fromEntity(supportLogRow: SupportLogRow) = SupportLogData(
            id = supportLogRow.id,
            mentorId = supportLogRow.mentorId,
            studentId = supportLogRow.studentId,
            log = supportLogRow.log,
            createdAt = supportLogRow.createdAt,
            updatedAt = supportLogRow.updatedAt
        )
    }
}