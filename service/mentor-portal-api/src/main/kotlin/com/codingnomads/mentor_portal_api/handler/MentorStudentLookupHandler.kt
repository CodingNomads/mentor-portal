package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.MentorStudentLookupUpdate
import com.codingnomads.mentor_portal_api.entity.business.MentorshipData
import com.codingnomads.mentor_portal_api.entity.enum.UserStatus
import com.codingnomads.mentor_portal_api.mapper.MentorStudentLookupMapper
import org.springframework.stereotype.Component

@Component
class MentorStudentLookupHandler (private val mentorStudentLookupMapper: MentorStudentLookupMapper) {
    /**
     * Get mentorStudentLookupData
     */
    fun getMentorStudentLookupData(studentId: Int): List<MentorshipData> = mentorStudentLookupMapper.selectMentorshipByStudent(studentId)
    /**
     * Update status
     */
    fun updateStatus(mentorStudentLookupUpdate: MentorStudentLookupUpdate, studentId: Int): List<MentorshipData>{
        println("In update status handler")
        val mentorStudentLookupId = mentorStudentLookupUpdate.id
        val description = mentorStudentLookupUpdate.description
        val validatedStatus = UserStatus.values().filter { it.description == description }
        if (validatedStatus != null){
            mentorStudentLookupMapper.updateStatus(mentorStudentLookupId, validatedStatus[0].code)
            println("Status validation: " + validatedStatus[0])
            println("Successful status validation.")
        }
        val mentorStudentLookupData = mentorStudentLookupMapper.selectMentorshipByStudent(studentId)
        println(mentorStudentLookupData)
        return mentorStudentLookupData
    }
}