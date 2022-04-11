package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.SupportLogData
import com.codingnomads.mentor_portal_api.entity.business.SupportLogFlagPayload
import com.codingnomads.mentor_portal_api.entity.business.SupportLogPayload
import com.codingnomads.mentor_portal_api.entity.data.SupportLogRow
import com.codingnomads.mentor_portal_api.mapper.StudentMapper
import com.codingnomads.mentor_portal_api.mapper.SupportLogMapper
import com.codingnomads.mentor_portal_api.mapper.UserMapper
import org.springframework.stereotype.Component

/**
 * Handles supportLogs
 */
@Component
class SupportLogHandler(
    private val supportLogMapper: SupportLogMapper,
    private val studentMapper: StudentMapper,
    private val userMapper: UserMapper
    ){
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
    fun updateSupportLog(supportLogFlagPayload: SupportLogFlagPayload,
                             studentId: Int,
                             supportLogId: Int){
        // variables to update support log
        val log = supportLogFlagPayload.log
        val flag = supportLogFlagPayload.flag
        val supportLogId = supportLogFlagPayload.supportLogId
        // update support log flag
        supportLogMapper.updateSupportLogFlag(supportLogId, flag, log)

        // once updated, update user flag to reflect proper flag status
        val userSupportLogs = supportLogMapper.selectSupportLogs(studentId)
        val someUser = userMapper.selectUserById(studentId)
        val flaggedLogs = userSupportLogs.filter { it.flag }.size

        // if 0 flagged logs, user.flag = false
        if (flaggedLogs == 0 && someUser.flag){
            val falseFlag = false
            userMapper.updateFlag(studentId, falseFlag)
        }
    }
    /**
     * POST new support log
     */
    fun createSupportLog(supportLogPayload: SupportLogPayload): SupportLogData{
        // create support log row for DB
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

        // if support log is flagged and user is NOT, update user to flagged
        val someUser = userMapper.selectUserById(supportLogPayload.studentId)
        if (supportLogPayload.flag && !someUser.flag){
            userMapper.updateFlag(someUser.id!!, supportLogPayload.flag)
        }



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