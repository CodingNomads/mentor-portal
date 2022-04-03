package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.SupportLogData
import com.codingnomads.mentor_portal_api.entity.business.SupportLogFlagPayload
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
     * Get all of a student's support logs
     */
    fun getSupportLogs(userId: Int) = supportLogMapper.selectSupportLogs(userId)
    /**
     * GET single support log for a student
     */
    fun getSingleSupportLog(userId: Int,
                            supportLogId: Int) = supportLogMapper.selectSingleSupportLog(userId, supportLogId)
    /**
     * UPDATE single support log for student
     */
    fun updateSupportLogFlag(supportLogFlagPayload: SupportLogFlagPayload,
                             studentId: Int,
                             supportLogId: Int){
        val log = supportLogFlagPayload.log
        val flag = supportLogFlagPayload.flag
        val supportLogId = supportLogFlagPayload.supportLogId

        supportLogMapper.updateSupportLogFlag(supportLogId, flag, log)
    }
    /**
     * POST new support log
     */
    fun createSupportLog(supportLogPayload: SupportLogPayload): SupportLogData{
        println(supportLogPayload)
        val supportLogRow = SupportLogRow(
            mentorId = supportLogPayload.mentorId,
            studentId = supportLogPayload.studentId,
            type = supportLogPayload.type,
            flag = supportLogPayload.flag,
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
            flag = supportLogRow.flag,
            duration = supportLogRow.duration.toInt(),
            log = supportLogRow.log,
            logDate = supportLogRow.logDate
        )
    }
}