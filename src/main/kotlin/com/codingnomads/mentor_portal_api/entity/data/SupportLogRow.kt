package com.codingnomads.mentor_portal_api.entity.data

import com.codingnomads.mentor_portal_api.entity.business.SupportLogData
import java.sql.Timestamp

data class SupportLogRow (
    val id: Int? = null,
    val mentorId: Int,
    val studentId: Int,
    val type: String,
    val flag: Boolean,
    val duration: Int,
    val log: String,
    val logDate: Timestamp
    ){
    companion object {
        fun fromEntity(supportLog: SupportLogData) = SupportLogRow(
            id = supportLog.id,
            mentorId = supportLog.mentorId,
            studentId = supportLog.studentId,
            type = supportLog.type,
            flag = supportLog.flag,
            duration = supportLog.duration,
            log = supportLog.log,
            logDate = supportLog.logDate
        )
    }
}