package com.codingnomads.mentor_portal_api.handlers

import com.codingnomads.mentor_portal_api.entities.data.UserModel
import com.codingnomads.mentor_portal_api.mappers.UserMapper
import org.springframework.stereotype.Component

/**
 * Handles users
 */
@Component
class UserHandler(private val userMapper: UserMapper) {
    /**
     * Gets a user by id
     *
     * @param userId The user id.
     * @return a UserModel
     */
    fun getUserById(userId: Int): UserModel {
        return userMapper.selectUserById(userId)
    }

    /**
     * Creates a new user
     *
     * @param user The user to be created.
     * @return The payload if insertion was successful.
     */
    fun createUser(user: UserModel): UserModel {
        userMapper.insertUser(user)
        return user
    }
}