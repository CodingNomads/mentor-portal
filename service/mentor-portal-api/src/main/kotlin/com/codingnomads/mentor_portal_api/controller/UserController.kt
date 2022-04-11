package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.User
import com.codingnomads.mentor_portal_api.handler.UserHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController (@Autowired private val userHandler: UserHandler) {
    /**
     * Get all users
     */
    @GetMapping("/users")
    fun getUsers(): List<User> = userHandler.getUsers()

    /**
     * Gets a user by id.
     *
     * @param userId The user id.
     * @return a UserModel.
     */
    @GetMapping("/users/{userId}")
    fun getUserById(@PathVariable userId: Int): User = userHandler.getUserById(userId)

    /**
     * Creates a new user
     *
     * @param payload The user to be created.
     * @return The payload if insertion was successful.
     */
    @PostMapping("/users")
    fun createUser(@RequestBody payload: User) = userHandler.createUser(payload)
}