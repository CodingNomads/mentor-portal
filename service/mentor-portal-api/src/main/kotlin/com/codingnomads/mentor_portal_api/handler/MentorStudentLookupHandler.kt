package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.MentorshipData
import com.codingnomads.mentor_portal_api.mapper.MentorStudentLookupMapper
import org.springframework.stereotype.Component

@Component
class MentorStudentLookupHandler (private val mentorStudentLookupMapper: MentorStudentLookupMapper) {
    /**
     * Get mentorStudentLookupData
     */
    fun getMentorStudentLookupData(studentId: Int): List<MentorshipData> = mentorStudentLookupMapper.selectMentorshipByStudent(studentId)
}