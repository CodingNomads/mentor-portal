package com.codingnomads.mentor_portal_api.mappers

import com.codingnomads.mentor_portal_api.entities.data.SecurityRow
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Insert

@Mapper
interface SecurityMapper {
    /**
     * Insert security information
     */
    @Insert(INSERT_SECURITY_STATEMENT + INSERT_SECURITY_VALUES)
    fun insertSecurity(securityRow: SecurityRow)

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
    }
}