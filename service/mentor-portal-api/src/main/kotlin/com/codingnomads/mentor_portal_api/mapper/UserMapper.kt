package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.data.UserRow
import com.codingnomads.mentor_portal_api.entity.business.User
import com.codingnomads.mentor_portal_api.entity.business.ClientAccess
import org.apache.ibatis.annotations.*

/**
 * Mapper for user table.
 */
@Mapper
interface UserMapper {

    /**
     * Select all users
     */
    @Select(SELECT_USERS_STATEMENT)
    fun selectUsers(): List<User>

    /**
     * Selects User from database by ID.
     *
     * @param id the user's id.
     * @return a UserModel
     */
    @Select(SELECT_USER_STATEMENT + FROM_USER_ID)
    fun selectUserById(id: Int): UserRow

    /**
     * Select User from database by email
     */
    @Select(SELECT_USER_BY_EMAIL_STATEMENT)
    fun selectUserByEmail(userEmail: String): ClientAccess

    /**
     * Inserts a new user.
     *
     * @param userRow the user payload to be inserted
     * @return the number of rows affected
     */
    @Insert(INSERT_USER_STATEMENT + INSERT_USER_VALUES)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertUser(userRow: UserRow) : Int

    /**
     * Update user flag boolean
     */
    @Update(UPDATE_USER_FLAG_STATEMENT)
    fun updateFlag(userId: Int, flag: Boolean): Int
    /**
     * Update user bio
     */
    @Update(UPDATE_USER_BIO_STATEMENT)
    fun updateBio(userId: Int, bio:String): Int
    /**
     * Update user status_code
     */
    @Update(UPDATE_USER_STATUS_CODE)
    fun updateStatusCode(userId: Int, statusCode: Int)
    /**
     * A companion object to hold sql statement strings
     */
    companion object {

        const val SELECT_USERS_STATEMENT =
            """
                SELECT
                user.id,
                user.email,
                user.first_name,
                user.last_name,
                user.role_code,
                user.status_code,
                user.flag,
                user.bio,
                user.timezone_offset
                FROM user
            """

        const val SELECT_USER_STATEMENT =
            """
            SELECT 
            id,
            email,
            first_name, 
            last_name, 
            role_code, 
            status_code,
            flag,
            bio,
            timezone_offset
            """

        const val FROM_USER_ID = "FROM user where id = #{value}"

        const val SELECT_USER_BY_EMAIL_STATEMENT =
            """
                SELECT DISTINCT
                user.id,
                security.is_admin
                FROM user
                JOIN security on user_id = user.id
                WHERE user.email = #{userEmail}
            """

        const val INSERT_USER_STATEMENT =
            """
            INSERT INTO user 
            (
            email,
            first_name, 
            last_name, 
            status_code, 
            role_code,
            bio,
            flag,
            timezone_offset
            )
            """
        const val INSERT_USER_VALUES = "VALUES (#{email}, #{firstName}, #{lastName}, #{statusCode}, #{roleCode}, #{bio}, #{flag}, #{timezoneOffset})"
        const val UPDATE_USER_FLAG_STATEMENT =
            """
               UPDATE user
               SET user.flag = #{flag}
               WHERE user.id = #{userId}
            """
        const val UPDATE_USER_BIO_STATEMENT =
            """
                UPDATE user
                SET user.bio = #{bio}
                WHERE user.id = #{userId}
            """
        const val UPDATE_USER_STATUS_CODE =
            """
                UPDATE user
                SET user.status_code = #{statusCode}
                WHERE user.id = #{userId}
            """
    }
}