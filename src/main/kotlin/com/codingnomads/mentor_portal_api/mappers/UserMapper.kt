package com.codingnomads.mentor_portal_api.mappers

import com.codingnomads.mentor_portal_api.entities.data.UserRow
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 * Mapper for user table.
 */
@Mapper
interface UserMapper {
    /**
     * Selects User from database by ID.
     *
     * @param id the user's id.
     * @return a UserModel
     */
    @Select(SELECT_USER_STATEMENT + FROM_USER_ID)
    fun selectUserById(id: Int): UserRow

    /**
     * Inserts a new user.
     *
     * @param userRow the user payload to be inserted
     * @return the number of rows affected
     */
    @Insert(INSERT_USER_STATEMENT + INSERT_USER_VALUES)
    fun insertUser(userRow: UserRow) : Int

    /**
     * A companion object to hold sql statement strings
     */
    companion object {
        const val SELECT_USER_STATEMENT =
            """
            SELECT 
            id,
            first_name, 
            last_name, 
            role_code, 
            status_code, 
            timezone_offset
            """

        const val FROM_USER_ID = "FROM user where id = #{value}"
        const val INSERT_USER_STATEMENT =
            """
            INSERT INTO user 
            (
            first_name, 
            last_name, 
            status_code, 
            role_code, 
            timezone_offset
            )
            """

        const val INSERT_USER_VALUES = "VALUES (#{firstName}, #{lastName}, #{statusCode}, #{roleCode}, #{timezoneOffset})"
    }
}