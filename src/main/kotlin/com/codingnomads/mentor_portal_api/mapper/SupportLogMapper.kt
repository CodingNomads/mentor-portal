package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.business.SupportLog
import com.codingnomads.mentor_portal_api.entity.business.SupportLogData
import com.codingnomads.mentor_portal_api.entity.data.SupportLogRow
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select

@Mapper
interface SupportLogMapper {
    /**
     * select student support logs
     */
    @Select(SELECT_SUPPORT_LOGS_STATEMENT)
    fun selectSupportLogs(id: Int): List<SupportLog>

    @Insert(INSERT_SUPPORT_LOG_STATEMENT + INSERT_SUPPORT_LOG_VALUES)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertSupportLog(supportLogRow: SupportLogRow)

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
                support_log.duration,
                support_log.log,
                support_log.log_date,
                support_log.created_at,
                support_log.updated_at
                FROM user
                JOIN support_log on user.id = mentor_id
                WHERE user.role_code = 10 AND student_id = #{value}
            """
        const val INSERT_SUPPORT_LOG_STATEMENT =
            """
                INSERT INTO support_log
                (
                mentor_id,
                student_id,
                type,
                duration,
                log,
                log_date
                )
            """
        const val INSERT_SUPPORT_LOG_VALUES =
            "VALUES (#{mentorId}, #{studentId}, #{type}, #{duration}, #{log}, #{logDate})"
    }
}