package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.Student
import com.codingnomads.mentor_portal_api.mapper.*
import org.springframework.stereotype.Component

/**
 * Handles Students
 */
@Component
class StudentHandler(
    private val contactMapper: ContactMapper,
    private val mentorMapper: MentorMapper,
    private val studentMapper: StudentMapper,
    private val supportLogMapper: SupportLogMapper,
    private val userMapper: UserMapper
    ) {
    /**
     * Get all Students
     */
    fun getStudents() = studentMapper.selectStudents()

    /**
     * Get student by id
     */
    fun getStudentById(studentId: Int): Student {
        val studentData = studentMapper.selectStudentById(studentId)
        val supportLog = supportLogMapper.selectSupportLogs(studentId)
        val mentorData = mentorMapper.selectAssignedMentor(studentId)

        return Student(
            id = studentData.id,
            firstName = studentData.firstName,
            lastName = studentData.lastName,
            roleCode = studentData.roleCode,
            statusCode = studentData.statusCode,
            email = studentData.email,
            telephone = studentData.telephone,
            forumUsername = studentData.forumUsername,
            slackUsername = studentData.slackUsername,
            assignedMentors = mentorData,
            supportLog = supportLog
        )
    }
}

