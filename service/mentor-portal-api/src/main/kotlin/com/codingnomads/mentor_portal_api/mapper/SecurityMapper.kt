package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.business.UserSecurityDTO
import com.codingnomads.mentor_portal_api.entity.data.SecurityRow
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select

@Mapper
interface SecurityMapper {
    /**
     * Insert security information
     */
    @Insert(INSERT_SECURITY_STATEMENT + INSERT_SECURITY_VALUES)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertSecurity(securityRow: SecurityRow)

    @Select(SELECT_USER_SECURITY_DETAILS)
    fun getUserSecurityDetails(email: String): UserSecurityDTO

    companion object{
        const val INSERT_SECURITY_STATEMENT =
            """
                INSERT INTO security
                (
                user_id,
                username,
                password_hash,
                is_admin
                )
            """
        const val INSERT_SECURITY_VALUES = "VALUES (#{userId}, #{username}, #{passwordHash}, #{isAdmin})"

        const val SELECT_USER_SECURITY_DETAILS =
            """
                SELECT DISTINCT
                contact.user_id,
                contact.email,
                security.password_hash,
                security.is_admin,
                user.status_code
                FROM contact
                JOIN security on user_id = contact.user_id
                JOIN user on user.id = contact.user_id
                WHERE email = #{value}
                
            """
    }
}