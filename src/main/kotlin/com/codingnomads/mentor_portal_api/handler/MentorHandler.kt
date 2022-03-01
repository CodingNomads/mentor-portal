package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.Mentor
import com.codingnomads.mentor_portal_api.entity.business.MentorData
import com.codingnomads.mentor_portal_api.entity.business.MentorPostPayload
import com.codingnomads.mentor_portal_api.entity.data.*
import com.codingnomads.mentor_portal_api.mapper.*
import org.springframework.stereotype.Component

/**
 * Handles Mentors
 */
@Component
class MentorHandler(
    private val contactMapper: ContactMapper,
    private val mentorMapper: MentorMapper,
    private val securityMapper: SecurityMapper,
    private val studentMapper: StudentMapper,
    private val userMapper: UserMapper,
    private val userConfigOptionMapper: UserConfigOptionMapper,
    private val userConfigValueMapper: UserConfigValueMapper
    ) {
    /**
     * Get all Mentors
     */
    fun getMentors(): List<Mentor> {
        val mentorList: MutableList<Mentor> = mutableListOf()
        val mentorData = mentorMapper.selectMentors()
        for (mentorObject in mentorData) {

            val id = mentorObject.id
            val assignedStudents = studentMapper.selectAssignedStudents(id!!)
            val someMentor = Mentor(
                id = mentorObject.id,
                firstName = mentorObject.firstName,
                lastName = mentorObject.lastName,
                roleCode = mentorObject.roleCode,
                statusCode = mentorObject.statusCode,
                email = mentorObject.email,
                telephone = mentorObject.telephone,
                forumUsername = mentorObject.forumUsername,
                slackUsername = mentorObject.slackUsername,
                assignedStudents = assignedStudents,
                studentCount = assignedStudents.size,
                maxStudents = mentorObject.value!!.toInt()
            )
            mentorList.add(someMentor)
        }
        return mentorList
    }
    /**
     * Get Mentor by id
     */
    fun getMentorById(mentorId: Int) : Mentor {
        val mentorData = mentorMapper.selectMentorById(mentorId)
        val assignedStudents = studentMapper.selectAssignedStudents(mentorId)

        return Mentor(
            id = mentorData.id,
            firstName = mentorData.firstName,
            lastName = mentorData.lastName,
            roleCode = mentorData.roleCode,
            statusCode = mentorData.statusCode,
            email = mentorData.email,
            telephone = mentorData.telephone,
            forumUsername = mentorData.forumUsername,
            slackUsername = mentorData.slackUsername,
            assignedStudents = assignedStudents,
            studentCount = assignedStudents.size,
            maxStudents = mentorData.value!!.toInt()
        )
    }

    /**
     * Create mentor
     */
    fun createMentor(mentorPostPayload: MentorPostPayload): MentorData {
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

        return MentorData(
            userId,
            userRow.firstName,
            userRow.lastName,
            userRow.roleCode,
            userRow.statusCode,
            contactRow.email,
            contactRow.telephone,
            contactRow.forumUsername,
            configOptionRow.name,
            configValueRow.value
        )
    }
}