package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.MentorDataRelation
import com.codingnomads.mentor_portal_api.entity.business.MentorPostPayload
import com.codingnomads.mentor_portal_api.entity.business.UserFlagPayload
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
    fun getMentors(): List<MentorDataRelation> = mentorHandler.getMentors()

    /**
     * Get Mentor by id
     *
     * @param  mentorId of user that is a mentor
     */
    @GetMapping("/mentors/{mentorId}")
    fun getMentorById(@PathVariable mentorId: Int) = mentorHandler.getMentorById(mentorId)
    /**
     * Update flag status for students
     */
    @PutMapping("/mentors/{mentorId}")
    fun updateFlag(@RequestBody payload: UserFlagPayload, @PathVariable mentorId: Int) = mentorHandler.updateFlag(payload)
    /**
     * Create mentor
     */
    @PostMapping("/mentors")
    fun createMentor(@RequestBody payload: MentorPostPayload) = mentorHandler.createMentor(payload)
}