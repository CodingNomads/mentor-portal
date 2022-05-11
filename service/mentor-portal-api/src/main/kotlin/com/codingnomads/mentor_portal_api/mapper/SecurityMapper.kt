package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.business.UserSecurityRow
import com.codingnomads.mentor_portal_api.entity.data.SecurityRow
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface SecurityMapper {
    /**
     * Insert security information
     */
    @Insert(INSERT_SECURITY_STATEMENT + INSERT_SECURITY_VALUES)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertSecurity(securityRow: SecurityRow)

    @Select(SELECT_USER_SECURITY_DETAILS_BY_EMAIL)
    fun getUserSecurityDetails(email: String): UserSecurityRow

    @Update(UPDATE_USER_PASSWORD_BY_USER_ID)
    fun updatePassword(encodedPassword: String?, userId: Long)

    companion object{
        const val INSERT_SECURITY_STATEMENT =
            """
                INSERT INTO security
                (
                user_id,
                password_hash,
                is_admin
                )
            """
        const val INSERT_SECURITY_VALUES = "VALUES (#{userId}, #{username}, #{passwordHash}, #{isAdmin})"

        const val SELECT_USER_SECURITY_DETAILS_BY_EMAIL =
            """
                SELECT DISTINCT
                user.id,
                user.email,
                security.password_hash,
                security.is_admin,
                user.status_code
                FROM user
                JOIN security on security.user_id = user.id
                WHERE user.email = #{value}
                AND user.status_code = 100
                LIMIT 1
            """

        const val UPDATE_USER_PASSWORD_BY_USER_ID =
            """
                UPDATE security
                SET
                security.passwordHash = #{encodedPassword}
                WHERE security.user_id = #{userId}
            """
    }
}