package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.business.SupportLog
import com.codingnomads.mentor_portal_api.entity.business.SupportLogData
import com.codingnomads.mentor_portal_api.entity.data.SupportLogRow
import org.apache.ibatis.annotations.*

@Mapper
interface SupportLogMapper {
    /**
     * SELECT ALL of a user's support logs
     */
    @Select(SELECT_SUPPORT_LOGS_STATEMENT)
    fun selectSupportLogs(userId: Int): List<SupportLog>
    /**
     * SELECT single support log for a user
     */
    @Select(SELECT_SINGLE_LOG_STATEMENT)
    fun selectSingleSupportLog(userId: Int, supportLogId: Int): SupportLog
    /**
     * INSERT new support log for student
     */
    @Insert(INSERT_SUPPORT_LOG_STATEMENT + INSERT_SUPPORT_LOG_VALUES)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertSupportLog(supportLogRow: SupportLogRow)

    /**
     * UPDATE single support log flag
     */
    @Update(UPDATE_SUPPORT_LOG_STATEMENT)
    fun updateSupportLogFlag(supportLogId: Int, flag: Boolean, log: String)

    companion object {
        const val SELECT_SUPPORT_LOGS_STATEMENT =
            """
               SELECT DISTINCT
                support_log.id,
                user.first_name,
                user.last_name,
                support_log.mentor_id,
                support_log.student_id,
                support_log.type,
                support_log.flag,
                support_log.duration,
                support_log.log,
                support_log.log_date,
                support_log.created_at,
                support_log.updated_at
                FROM user
                JOIN support_log on user.id = mentor_id
                WHERE user.role_code = 10 AND student_id = #{userId}
            """
        const val SELECT_SINGLE_LOG_STATEMENT =
            """
                SELECT DISTINCT
                support_log.id,
                user.first_name,
                user.last_name,
                support_log.mentor_id,
                support_log.student_id,
                support_log.type,
                support_log.flag,
                support_log.duration,
                support_log.log,
                support_log.log_date,
                support_log.created_at,
                support_log.updated_at
                FROM user
                JOIN support_log on user.id = mentor_id
                WHERE user.role_code = 10 AND student_id = #{userId} AND support_log.id = #{supportLogId}
            """
        const val INSERT_SUPPORT_LOG_STATEMENT =
            """
                INSERT INTO support_log
                (
                mentor_id,
                student_id,
                type,
                flag,
                duration,
                log,
                log_date
                )
            """
        const val INSERT_SUPPORT_LOG_VALUES =
            "VALUES (#{mentorId}, #{studentId}, #{type}, #{flag}, #{duration}, #{log}, #{logDate})"
        const val UPDATE_SUPPORT_LOG_STATEMENT =
            """
                UPDATE support_log
                SET 
                support_log.flag = #{flag},
                support_log.log = #{log}
                WHERE support_log.id = #{supportLogId}
            """
    }
}