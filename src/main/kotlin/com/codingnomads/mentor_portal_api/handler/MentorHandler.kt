package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.Mentor
import com.codingnomads.mentor_portal_api.entity.business.MentorPostPayload
import com.codingnomads.mentor_portal_api.entity.data.*
import com.codingnomads.mentor_portal_api.mapper.*
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
    private val userConfigOptionMapper: UserConfigOptionMapper,
    private val userConfigValueMapper: UserConfigValueMapper
    ) {
    /**
     * Get all Mentors
     */
    fun getMentors() = mentorMapper.selectMentors()

    /**
     * Get Mentor by id
     */
    fun getMentorById(mentorId: Int) = mentorMapper.selectMentorById(mentorId)

    /**
     * Create mentor
     */
    fun createMentor(mentorPostPayload: MentorPostPayload): Mentor {
        val userRow = UserRow(
            firstName = mentorPostPayload.firstName,
            lastName = mentorPostPayload.lastName,
            roleCode = 10,
            statusCode = 100,
            timezoneOffset = mentorPostPayload.timezoneOffset
        )

        userMapper.insertUser(userRow)

        val userId = userRow.id!!;

        val contactRow = ContactRow(
            userId = userId,
            email = mentorPostPayload.email,
            telephone = mentorPostPayload.telephone,
            forumUsername = mentorPostPayload.username,
            slackUsername = mentorPostPayload.slackUsername
        )

        contactMapper.insertContact(contactRow)

        val securityRow = SecurityRow(
            userId = userId,
            username = mentorPostPayload.username,
            passwordHash = mentorPostPayload.passwordHash,
            isAdmin = mentorPostPayload.isAdmin
        )

        securityMapper.insertSecurity(securityRow)

        val configOptionRow = userConfigOptionMapper.selectOptionByName("maxStudents")
        val configValueRow = ConfigValueRow(
            optionId = configOptionRow.id,
            userId = userId,
            value = mentorPostPayload.maxStudents.toString()
        )

        userConfigValueMapper.insertConfigValue(configValueRow)

        return Mentor()
    }
}