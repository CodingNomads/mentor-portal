package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.data.SecurityRow
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options

@Mapper
interface SecurityMapper {
    /**
     * Insert security information
     */
    @Insert(INSERT_SECURITY_STATEMENT + INSERT_SECURITY_VALUES)
    @Options(useGeneratedKeys = true, keyProperty = "id")
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