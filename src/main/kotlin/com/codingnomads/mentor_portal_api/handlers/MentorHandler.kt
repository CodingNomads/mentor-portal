package com.codingnomads.mentor_portal_api.handlers

import com.codingnomads.mentor_portal_api.entities.business.Mentor
import com.codingnomads.mentor_portal_api.entities.business.User
import com.codingnomads.mentor_portal_api.entities.business.UserPostPayload
import com.codingnomads.mentor_portal_api.entities.data.ContactRow
import com.codingnomads.mentor_portal_api.entities.data.SecurityRow
import com.codingnomads.mentor_portal_api.entities.data.UserRow
import com.codingnomads.mentor_portal_api.mappers.ContactMapper
import com.codingnomads.mentor_portal_api.mappers.MentorMapper
import com.codingnomads.mentor_portal_api.mappers.SecurityMapper
import com.codingnomads.mentor_portal_api.mappers.StudentMapper
import com.codingnomads.mentor_portal_api.mappers.UserMapper
import org.springframework.stereotype.Component

/**
 * Handles Mentors
 */
@Component
class MentorHandler(
    private val mentorMapper: MentorMapper,
    private val userMapper: UserMapper,
    private val contactMapper: ContactMapper,
    private val securityMapper: SecurityMapper,
    ) {
    /**
     * Get all Mentors
     */
    fun getMentors() = mentorMapper.selectMentors()
//    val mentorList = mentorMapper.selectMentors()
//    val mentorIdList = mentorList.stream()
//    val Map<int,List<userRow>Student> = studentMapper.getStudentsByMentorIds(mentorId)

    /**
     * Get Mentor by id
     */
    fun getMentorById(mentorId: Int) = mentorMapper.selectMentorById(mentorId)

    /**
     * Create mentor
     */
    fun createMentor(userPostPayload: UserPostPayload): Mentor {
        val userRow = UserRow(
            id = userPostPayload.id,
            firstName = userPostPayload.firstName,
            lastName = userPostPayload.lastName,
            roleCode = userPostPayload.roleCode,
            statusCode = userPostPayload.statusCode,
            timezoneOffset = userPostPayload.timezoneOffset
        )

        val userId = userMapper.insertUser(userRow)

        val contactRow = ContactRow(
            userId = userId,
            email = userPostPayload.email,
            telephone = userPostPayload.telephone,
            forumUsername = userPostPayload.username,
            slackUsername = userPostPayload.slackUsername
        )

        contactMapper.insertContact(contactRow)

        val securityRow = SecurityRow(
            userId = userId,
            username = userPostPayload.username,
            passwordHash = userPostPayload.passwordHash,
            isAdmin = userPostPayload.isAdmin
        )

        securityMapper.insertSecurity(securityRow)
        return Mentor(
            firstName = userPostPayload.firstName,
            lastName = userPostPayload.lastName,
            roleCode = userPostPayload.roleCode,
            statusCode = userPostPayload.statusCode,
            email = userPostPayload.email,
            telephone = userPostPayload.telephone
        )
    }
}