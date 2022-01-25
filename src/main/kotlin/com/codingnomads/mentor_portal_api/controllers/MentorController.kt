package com.codingnomads.mentor_portal_api.controllers

import com.codingnomads.mentor_portal_api.entities.business.Mentor
import com.codingnomads.mentor_portal_api.entities.business.UserPostPayload
import com.codingnomads.mentor_portal_api.handlers.MentorHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/mentors")
class MentorController(@Autowired private val mentorHandler: MentorHandler) {
    /**
     * Get all Mentors
     */
    @GetMapping("")
    fun getMentors(): List<Mentor> = mentorHandler.getMentors()

    /**
     * Get Mentor by id
     *
     * @param  mentorId of user that is a mentor name
     */
    @GetMapping("/{mentorId}")
    fun getMentorById(@PathVariable mentorId: Int): Mentor = mentorHandler.getMentorById(mentorId)

    /**
     * Create mentor
     */
    @PostMapping("")
    fun createMentor(@RequestBody payload: UserPostPayload) = mentorHandler.createMentor(payload)
}