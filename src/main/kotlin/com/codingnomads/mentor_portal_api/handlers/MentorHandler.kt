package com.codingnomads.mentor_portal_api.handlers

import com.codingnomads.mentor_portal_api.entities.business.Mentor
import com.codingnomads.mentor_portal_api.mappers.MentorMapper
import org.springframework.stereotype.Component

/**
 * Handles Mentors
 */
@Component
class MentorHandler(private val mentorMapper: MentorMapper) {
    /**
     * Get all Mentors
     */
    fun getMentors() = mentorMapper.selectMentors()

    /**
     * Get Mentor by id
     */
    fun getMentorById(mentorId: Int) = mentorMapper.selectMentorById(mentorId)
}