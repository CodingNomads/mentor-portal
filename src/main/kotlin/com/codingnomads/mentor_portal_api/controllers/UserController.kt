package com.codingnomads.mentor_portal_api.controllers

import com.codingnomads.mentor_portal_api.entities.business.User
import com.codingnomads.mentor_portal_api.entities.data.UserRow
import com.codingnomads.mentor_portal_api.handlers.UserHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController (@Autowired private val userHandler: UserHandler) {
    /**
     * Gets a user by id.
     *
     * @param userId The user id.
     * @return a UserModel.
     */
    @GetMapping("/user/{userId}")
    fun getUserById(@PathVariable userId: Int): User = userHandler.getUserById(userId)

    /**
     * Creates a new user
     *
     * @param payload The user to be created.
     * @return The payload if insertion was successful.
     */
    @PostMapping("/user")
    fun createUser(@RequestBody payload: User) = userHandler.createUser(payload)
}