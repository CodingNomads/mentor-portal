package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.Mentor
import com.codingnomads.mentor_portal_api.entity.business.MentorData
import com.codingnomads.mentor_portal_api.entity.business.MentorPostPayload
import com.codingnomads.mentor_portal_api.handler.MentorHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class MentorController(@Autowired private val mentorHandler: MentorHandler) {
    /**
     * Get all Mentors
     */
    @GetMapping("/mentors")
    fun getMentors(): List<Mentor> = mentorHandler.getMentors()

    /**
     * Get Mentor by id
     *
     * @param  mentorId of user that is a mentor
     */
    @GetMapping("/mentors/{mentorId}")
    fun getMentorById(@PathVariable mentorId: Int) = mentorHandler.getMentorById(mentorId)

    /**
     * Create mentor
     */
    @PostMapping("/mentors")
    fun createMentor(@RequestBody payload: MentorPostPayload) = mentorHandler.createMentor(payload)
}