package com.codingnomads.mentor_portal_api.handlers

import com.codingnomads.mentor_portal_api.entities.business.User
import com.codingnomads.mentor_portal_api.entities.business.Contact
import com.codingnomads.mentor_portal_api.entities.business.UserPostPayload
import com.codingnomads.mentor_portal_api.entities.data.ContactRow
import com.codingnomads.mentor_portal_api.entities.data.SecurityRow
import com.codingnomads.mentor_portal_api.entities.data.UserRow
import com.codingnomads.mentor_portal_api.mappers.UserMapper
import org.springframework.stereotype.Component

/**
 * Handles users
 */
@Component
class UserHandler(private val userMapper: UserMapper) {

    /**
     * Get all users
     */
    fun getUsers() = userMapper.selectUsers()

    /**
     * Gets a user by id
     *
     * @param userId The user id.
     * @return a UserModel
     */
    fun getUserById(userId: Int): User {
        return User.fromEntity(userMapper.selectUserById(userId))
    }

    /**
     * Creates a new user
     *
     * @param user The user to be created.
     * @return The payload if insertion was successful.
     */
    fun createUser(user: User): UserRow {
        val userRow: UserRow = UserRow.fromEntity(user)

        userMapper.insertUser(userRow)
        return userRow
    }
}