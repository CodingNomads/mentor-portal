package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.*
import com.codingnomads.mentor_portal_api.entity.data.*
import com.codingnomads.mentor_portal_api.entity.enum.CourseOption
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
                    bio = mentor.bio,
                    location = mentor.location,
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
                    bio = mentor.bio,
                    location = mentor.location,
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
            bio = mentorData.bio,
            location = mentorData.location,
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
     * Update mentor flag
     */
    fun updateMentor(userUpdatePayload: UserUpdatePayload): MentorDataRelation {
        println(userUpdatePayload)
        // check payload for update fields
        if (userUpdatePayload.flag != null) {
            // update flag
            userMapper.updateFlag(userUpdatePayload.userId, userUpdatePayload.flag)
        }
        if (userUpdatePayload.bio != null){
            userMapper.updateBio(userUpdatePayload.userId, userUpdatePayload.bio)
        }
        // return updated mentorDataRelation object
        val assignedStudents = studentMapper.selectAssignedStudents(userUpdatePayload.userId)
        val maxNumber = userConfigValueMapper.selectMaxStudentValue(userUpdatePayload.userId)
        val someMentorData = mentorMapper.selectMentorById(userUpdatePayload.userId)
        val mentorProficiencies = userConfigValueMapper.selectProficienciesValue(userUpdatePayload.userId)
        val proficienciesList: MutableList<String> = mutableListOf()

        for (data in mentorProficiencies){
            proficienciesList.add(data.proficiencies)
        }

        return MentorDataRelation(
            id = userUpdatePayload.userId,
            firstName = someMentorData.firstName,
            lastName = someMentorData.lastName,
            roleCode = someMentorData.roleCode,
            statusCode = someMentorData.statusCode,
            flag = someMentorData.flag,
            bio = someMentorData.bio,
            location = someMentorData.location,
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
        // validate proficiencies sent in mentorPostPayload
        val proficienciesLowerCase = mentorPostPayload.proficiencies.map { it.lowercase() }
        val proficienciesVerification = CourseOption.values().filter { it.title in proficienciesLowerCase }
        println(mentorPostPayload)
        println(proficienciesLowerCase)
        println(proficienciesVerification)
        // if all submitted proficiencies are valid
        if (proficienciesVerification.size == mentorPostPayload.proficiencies.size){
            val userRow = UserRow(
                email = mentorPostPayload.email,
                firstName = mentorPostPayload.firstName,
                lastName = mentorPostPayload.lastName,
                roleCode = 10,
                statusCode = 100,
                flag = false,
                bio = mentorPostPayload.bio,
                timezoneOffset = mentorPostPayload.timezoneOffset
            )

            userMapper.insertUser(userRow)

            val userId = userRow.id!!;

            val contactRow = ContactRow(
                userId = userId,
                telephone = mentorPostPayload.telephone,
                location = mentorPostPayload.location,
                forumUsername = mentorPostPayload.forumUsername,
                slackUsername = mentorPostPayload.slackUsername
            )

            contactMapper.insertContact(contactRow)

//        val securityRow = SecurityRow(
//            userId = userId,
//            username = mentorPostPayload.username,
//            passwordHash = mentorPostPayload.passwordHash,
//            isAdmin = mentorPostPayload.isAdmin
//        )
//
//        securityMapper.insertSecurity(securityRow)

            val maxStudentsOptionRow = userConfigOptionMapper.selectOptionByName("maxStudents")
            val maxStudentsValueRow = ConfigValueRow(
                optionId = maxStudentsOptionRow.id,
                userId = userId,
                value = mentorPostPayload.maxStudents.toString()
            )

            userConfigValueMapper.insertConfigValue(maxStudentsValueRow)

            val proficienciesOptionRow = userConfigOptionMapper.selectOptionByName("proficiencies")
            println(proficienciesOptionRow)
            println(mentorPostPayload)
            for (proficiency in mentorPostPayload.proficiencies){
                val proficienciesValueRow = ConfigValueRow(
                    optionId = proficienciesOptionRow.id,
                    userId = userId,
                    value = proficiency
                )
                userConfigValueMapper.insertConfigValue(proficienciesValueRow)
            }

            return MentorData(
                id = userId,
                email = userRow.email,
                firstName = userRow.firstName,
                lastName = userRow.lastName,
                roleCode = userRow.roleCode,
                statusCode = userRow.statusCode,
                flag = userRow.flag,
                bio = userRow.bio,
                location = contactRow.location,
                telephone = contactRow.telephone,
                forumUsername = contactRow.forumUsername,
                slackUsername = contactRow.slackUsername,
            )
        }else{
            throw Error("Invalid proficiencies submitted")
        }
    }
}