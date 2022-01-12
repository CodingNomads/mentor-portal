package com.codingnomads.mentor_portal_api.controllers

import com.codingnomads.mentor_portal_api.entities.business.Mentor
import com.codingnomads.mentor_portal_api.entities.data.MentorRow
import com.codingnomads.mentor_portal_api.handlers.MentorHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")

class MentorController(@Autowired private val mentorHandler: MentorHandler) {
    /**
     * Get all Mentors
     */
    @GetMapping("/mentors")
    fun getMentors(): Mentor = mentorHandler.getMentors()
}