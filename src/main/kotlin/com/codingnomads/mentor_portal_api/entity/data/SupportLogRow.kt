package com.codingnomads.mentor_portal_api.entity.data

import com.codingnomads.mentor_portal_api.entity.business.SupportLogData
import java.sql.Timestamp

data class SupportLogRow (
    val id: Int? = null,
    val mentorId: Int,
    val studentId: Int,
    val log: String,
    val createdAt: Timestamp,
    val updatedAt: Timestamp
    ){
    companion object {
        fun fromEntity(supportLog: SupportLogData) = SupportLogRow(
            id = supportLog.id,
            mentorId = supportLog.mentorId,
            studentId = supportLog.studentId,
            log = supportLog.log,
            createdAt = supportLog.createdAt,
            updatedAt = supportLog.updatedAt
        )
    }
}