package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.*
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
    private val mentorStudentLookupMapper: MentorStudentLookupMapper,
    private val securityMapper: SecurityMapper,
    private val studentMapper: StudentMapper,
    private val supportLogMapper: SupportLogMapper,
    private val userMapper: UserMapper,
    private val userConfigOptionMapper: UserConfigOptionMapper,
    private val userConfigValueMapper: UserConfigValueMapper
    ) {
    /**
     * Get all Mentors
     */
    fun getMentors(): List<MentorDataRelation> {
        // lists
        val mentorList: MutableList<MentorDataRelation> = mutableListOf()
        val mentorDataList = mentorMapper.selectMentors()
        val mentorshipDataList = mentorStudentLookupMapper.selectMentorships()
        val studentsWithMentorsList = studentMapper.selectAllStudentsWithMentors()
        val mentorsConfigValues = userConfigValueMapper.selectAllMentorValues()
        // maps
        val studentsWithMentorsByIdMap: Map<Int, StudentData> = studentsWithMentorsList.associateBy { it.id!! }
        val mentorshipDataMap: Map<Int, MentorshipData> = mentorshipDataList.associateBy { it.mentorId }

        for (mentor in mentorDataList){
            val filteredMentorshipPair = mentorshipDataList.filter { it.mentorId == mentor.id }
            val filteredAssignedStudents = filteredMentorshipPair.map { student -> studentsWithMentorsByIdMap[student.studentId]!! }
            val maxStudentCount = mentorsConfigValues.filter{ it.userId == mentor.id }.filter { it.optionId == 1 }[0].value.toInt()
            val proficiencies = mentorsConfigValues.filter { it.userId == mentor.id }.filter { it.optionId == 2 }.map { proficiencyList -> proficiencyList.value }

            if (mentorshipDataMap[mentor.id] != null){
                val someMentor = MentorDataRelation(
                    id = mentor.id,
                    firstName = mentor.firstName,
                    lastName = mentor.lastName,
                    roleCode = mentor.roleCode,
                    statusCode = mentor.statusCode,
                    flag = mentor.flag,
                    email = mentor.email,
                    telephone = mentor.telephone,
                    forumUsername = mentor.forumUsername,
                    slackUsername = mentor.slackUsername,
                    assignedStudents = filteredAssignedStudents,
                    studentCount = filteredAssignedStudents.size,
                    maxStudents = maxStudentCount,
                    proficiencies = proficiencies
            )
                mentorList.add(someMentor)
            }else{
                val someMentor = MentorDataRelation(
                    id = mentor.id,
                    firstName = mentor.firstName,
                    lastName = mentor.lastName,
                    roleCode = mentor.roleCode,
                    statusCode = mentor.statusCode,
                    flag = mentor.flag,
                    email = mentor.email,
                    telephone = mentor.telephone,
                    forumUsername = mentor.forumUsername,
                    slackUsername = mentor.slackUsername,
                    assignedStudents = listOf(),
                    studentCount = 0,
                    maxStudents = maxStudentCount,
                    proficiencies = proficiencies
                )
                mentorList.add(someMentor)
            }
        }
        return mentorList
    }

    /**
     * Get Mentor by id
     */
    fun getMentorById(mentorId: Int) : Mentor {
        val mentorData = mentorMapper.selectMentorById(mentorId)
        val assignedStudents = studentMapper.selectAssignedStudents(mentorId)
        val maxNumber = userConfigValueMapper.selectMaxStudentValue(mentorId)
        val mentorProficiencies = userConfigValueMapper.selectProficienciesValue(mentorId)
        val proficienciesList: MutableList<String> = mutableListOf()
        val mentorSupportLogs = supportLogMapper.selectSupportLogs(mentorId)

        for (data in mentorProficiencies){
            proficienciesList.add(data.proficiencies)
        }

        val studentCount: Int = try { assignedStudents.size } catch (e: java.lang.NullPointerException) { 0 }

        return Mentor(
            id = mentorData.id,
            firstName = mentorData.firstName,
            lastName = mentorData.lastName,
            roleCode = mentorData.roleCode,
            statusCode = mentorData.statusCode,
            flag = mentorData.flag,
            email = mentorData.email,
            telephone = mentorData.telephone,
            forumUsername = mentorData.forumUsername,
            slackUsername = mentorData.slackUsername,
            assignedStudents = assignedStudents,
            studentCount = studentCount,
            maxStudents = maxNumber.maxStudentsCount.toInt(),
            proficiencies = proficienciesList,
            supportLog = mentorSupportLogs
        )
    }
    /**
     * Update student flag
     */
    fun updateFlag(flagPayload: UserFlagPayload): MentorDataRelation {
        // update flag boolean
        userMapper.updateFlag(flagPayload.flag, flagPayload.userId)
        // return updated mentorDataRelation object
        val assignedStudents = studentMapper.selectAssignedStudents(flagPayload.userId)
        val maxNumber = userConfigValueMapper.selectMaxStudentValue(flagPayload.userId)
        val someMentorData = mentorMapper.selectMentorById(flagPayload.userId)
        val mentorProficiencies = userConfigValueMapper.selectProficienciesValue(flagPayload.userId)
        val proficienciesList: MutableList<String> = mutableListOf()

        for (data in mentorProficiencies){
            proficienciesList.add(data.proficiencies)
        }

        return MentorDataRelation(
            id = flagPayload.userId,
            firstName = someMentorData.firstName,
            lastName = someMentorData.lastName,
            roleCode = someMentorData.roleCode,
            statusCode = someMentorData.statusCode,
            flag = someMentorData.flag,
            email = someMentorData.email,
            telephone = someMentorData.telephone,
            forumUsername = someMentorData.forumUsername,
            slackUsername = someMentorData.slackUsername,
            assignedStudents = assignedStudents,
            studentCount = assignedStudents.size,
            maxStudents = maxNumber.maxStudentsCount.toInt(),
            proficiencies = proficienciesList
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
            flag = false,
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

        val maxStudentsOptionRow = userConfigOptionMapper.selectOptionByName("maxStudents")
        val maxStudentsValueRow = ConfigValueRow(
            optionId = maxStudentsOptionRow.id,
            userId = userId,
            value = mentorPostPayload.maxStudents.toString()
        )

        userConfigValueMapper.insertConfigValue(maxStudentsValueRow)

        val proficienciesOptionRow = userConfigOptionMapper.selectOptionByName("proficiencies")
        val proficienciesValueRow = ConfigValueRow(
            optionId = proficienciesOptionRow.id,
            userId = userId,
            value = mentorPostPayload.proficiencies
        )

        userConfigValueMapper.insertConfigValue(proficienciesValueRow)

        return MentorData(
            userId,
            userRow.firstName,
            userRow.lastName,
            userRow.roleCode,
            userRow.statusCode,
            userRow.flag,
            contactRow.email,
            contactRow.telephone,
            contactRow.forumUsername,
            contactRow.slackUsername,
        )
    }
}