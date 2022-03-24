package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.SupportLogData
import com.codingnomads.mentor_portal_api.entity.business.SupportLogPayload
import com.codingnomads.mentor_portal_api.entity.data.SupportLogRow
import com.codingnomads.mentor_portal_api.mapper.SupportLogMapper
import org.springframework.stereotype.Component

/**
 * Handles supportLogs
 */
@Component
class SupportLogHandler(private val supportLogMapper: SupportLogMapper){
    /**
     * post supportLog
     */
    fun createSupportLog(supportLogPayload: SupportLogPayload): SupportLogData{
        println(supportLogPayload)
        val supportLogRow = SupportLogRow(
            mentorId = supportLogPayload.mentorId,
            studentId = supportLogPayload.studentId,
            type = supportLogPayload.type,
            duration = supportLogPayload.duration.toInt(),
            log = supportLogPayload.log,
            logDate = supportLogPayload.logDate,
        )
        supportLogMapper.insertSupportLog(supportLogRow)

        val supportLogId = supportLogRow.id!!;

        return SupportLogData(
            id = supportLogId,
            mentorId = supportLogRow.mentorId,
            studentId = supportLogRow.studentId,
            type = supportLogRow.type,
            duration = supportLogRow.duration.toInt(),
            log = supportLogRow.log,
            logDate = supportLogRow.logDate
        )
    }
}