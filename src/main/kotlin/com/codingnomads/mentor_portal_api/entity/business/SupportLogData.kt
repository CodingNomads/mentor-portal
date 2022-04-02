package com.codingnomads.mentor_portal_api.entity.business

import com.codingnomads.mentor_portal_api.entity.data.SupportLogRow
import java.sql.Timestamp

data class SupportLogData (
    val id: Int? = null,
    val mentorId: Int,
    val studentId: Int,
    val type: String,
    val flag: Boolean,
    val duration: Int,
    val log: String,
    val logDate: Timestamp
    ) {
    companion object {
        fun fromEntity(supportLogRow: SupportLogRow) = SupportLogData(
            id = supportLogRow.id,
            mentorId = supportLogRow.mentorId,
            studentId = supportLogRow.studentId,
            type = supportLogRow.type,
            flag = supportLogRow.flag,
            duration = supportLogRow.duration,
            log = supportLogRow.log,
            logDate = supportLogRow.logDate
        )
    }
}