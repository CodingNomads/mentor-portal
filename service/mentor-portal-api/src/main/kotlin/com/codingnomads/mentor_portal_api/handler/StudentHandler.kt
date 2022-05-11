package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.*
import com.codingnomads.mentor_portal_api.entity.data.*
import com.codingnomads.mentor_portal_api.entity.enum.CourseOption
import com.codingnomads.mentor_portal_api.entity.enum.MentorshipOption
import com.codingnomads.mentor_portal_api.mapper.*
import org.springframework.stereotype.Component

/**
 * Handles Students
 */
@Component
class StudentHandler(
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
     * Get all Students
     */
    fun getStudents(): List<StudentDataRelation> {
        // lists
        val studentList: MutableList<StudentDataRelation> = mutableListOf()
        val studentDataList = studentMapper.selectStudents()                            // student-user and contact info
        val mentorshipDataList = mentorStudentLookupMapper.selectMentorships()          // for mentor-student pairs
        val mentorsList = mentorMapper.selectAllMentorsWithStudents()                   // for List<MentorData> that have students
        val studentsConfigValuesList = userConfigValueMapper.selectAllStudentValues()   // for mapping their courseTrack
        println(studentsConfigValuesList)
        // maps
        val mentorsWithStudentsByIdMap: Map<Int, MentorData> = mentorsList.associateBy { it.id!! }
        val mentorshipPairMap: Map<Int, MentorshipData> = mentorshipDataList.associateBy { it.studentId }
//        val studentConfigValuesMap: Map<Int, UserConfigData> = studentsConfigValuesList.associateBy { it.userId }
//        println(studentConfigValuesMap)

        for (student in studentDataList){
            val filteredStudentConfigValues = studentsConfigValuesList.filter { it.userId == student.id }
            val studentCourseTrack = filteredStudentConfigValues.filter { it.optionId == 3 }
            val studentMentorshipOption = filteredStudentConfigValues.filter { it.optionId == 4 }
            println(filteredStudentConfigValues)
            val filteredMentorshipPair = mentorshipDataList.filter { it.studentId == student.id }
            val filteredMentors = filteredMentorshipPair.filter { true }.map { mentor -> mentorsWithStudentsByIdMap[mentor.mentorId]!! }
            // if students have a mentor they also have a mentorshipOption
            if (mentorshipPairMap[student.id] != null){
                val someStudent = StudentDataRelation(
                    id = student.id!!,
                    firstName = student.firstName,
                    lastName = student.lastName,
                    roleCode = student.roleCode,
                    statusCode = student.statusCode,
                    flag = student.flag,
                    bio = student.bio,
                    location = student.location,
                    email = student.email,
                    telephone = student.telephone,
                    forumUsername = student.forumUsername,
                    slackUsername = student.slackUsername,
                    assignedMentors = filteredMentors,
                    courseTrack = studentCourseTrack[0].value,
                    mentorshipOption = studentMentorshipOption[0].value
                )
                studentList.add(someStudent)
            }else{
                val someStudent = StudentDataRelation(
                    id = student.id!!,
                    firstName = student.firstName,
                    lastName = student.lastName,
                    roleCode = student.roleCode,
                    statusCode = student.statusCode,
                    flag = student.flag,
                    bio = student.bio,
                    location = student.location,
                    email = student.email,
                    telephone = student.telephone,
                    forumUsername = student.forumUsername,
                    slackUsername = student.slackUsername,
                    assignedMentors = mutableListOf(),
                    courseTrack = studentCourseTrack[0].value,
                    mentorshipOption = ""
                )
                studentList.add(someStudent)
            }
        }
        return studentList
    }

    /**
     * Get student by id
     */
    fun getStudentById(studentId: Int): Student {
        val studentData = studentMapper.selectStudentById(studentId)
        val supportLog = supportLogMapper.selectSupportLogs(studentId)
        val mentorData = mentorMapper.selectAssignedMentor(studentId)
        val studentCourseTrack = userConfigValueMapper.selectStudentCourseTrack(studentId)
        val studentMentorshipOption = userConfigValueMapper.selectStudentMentorShipOption(studentId)

        return Student(
            id = studentData.id,
            firstName = studentData.firstName,
            lastName = studentData.lastName,
            roleCode = studentData.roleCode,
            statusCode = studentData.statusCode,
            flag = studentData.flag,
            bio = studentData.bio,
            location = studentData.location,
            email = studentData.email,
            telephone = studentData.telephone,
            forumUsername = studentData.forumUsername,
            slackUsername = studentData.slackUsername,
            assignedMentors = mentorData,
            supportLog = supportLog,
            courseTrack = studentCourseTrack.courseTrack,
            mentorshipOption = studentMentorshipOption.mentorshipOption
        )
    }

    /**
     * Create a student
     */
    fun createStudent(studentPostPayload: StudentPostPayload): StudentDataRelation{
        // validate courseTrack sent in studentPostPayload
        val courseTrackLowerCase = studentPostPayload.courseTrack.lowercase()
        val courseTrackVerification = CourseOption.values().filter { it.title == courseTrackLowerCase }
        // validate the mentorshipOption sent in the student payload
        val mentorshipOptionLowercase = studentPostPayload.mentorshipOption.lowercase()
        val mentorshipOptionVerification = MentorshipOption.values().filter { it.title == mentorshipOptionLowercase }
        println(studentPostPayload)
        println(courseTrackLowerCase)
        println(courseTrackVerification)
        println(mentorshipOptionLowercase)
        println(mentorshipOptionVerification)
        // if courseTrack is valid
        if (courseTrackVerification.isNotEmpty() && mentorshipOptionVerification.isNotEmpty()){
            // user table fields
            val userRow = UserRow(
                email = studentPostPayload.email,
                firstName = studentPostPayload.firstName,
                lastName = studentPostPayload.lastName,
                roleCode = 20,
                statusCode = 100,
                flag = false,
                bio = studentPostPayload.bio,
                timezoneOffset = studentPostPayload.timezoneOffset
            )
            userMapper.insertUser(userRow)
            val userId = userRow.id!!

            // contact table fields
            val contactRow = ContactRow(
                userId = userId,
                telephone = studentPostPayload.telephone,
                location = studentPostPayload.location,
                forumUsername = studentPostPayload.forumUsername,
                slackUsername = studentPostPayload.slackUsername
            )
            contactMapper.insertContact(contactRow)

//        // security table fields
//        val securityRow = SecurityRow(
//            userId = userId,
//            username = studentPostPayload.username,
//            passwordHash = studentPostPayload.passwordHash,
//            isAdmin = false
//        )
//        securityMapper.insertSecurity(securityRow)

            // config data
            // courseTrack
            val courseTrackOptionRow = userConfigOptionMapper.selectOptionByName("courseTrack")
            val courseTrackValueRow = ConfigValueRow(
                optionId = courseTrackOptionRow.id,
                userId = userId,
                value = studentPostPayload.courseTrack
            )
            userConfigValueMapper.insertConfigValue(courseTrackValueRow)
            // mentorshipOption
            val mentorshipOptionRow = userConfigOptionMapper.selectOptionByName("mentorshipOption")
            val mentorshipOptionValueRow = ConfigValueRow(
                optionId = mentorshipOptionRow.id,
                userId = userId,
                value = studentPostPayload.mentorshipOption
            )
            userConfigValueMapper.insertConfigValue(mentorshipOptionValueRow)

            // assign mentor if studentPostPayload.assignedMentors != null
            // otherwise make assignedMentors and empty list
            if (studentPostPayload.assignedMentors != null) {
                val mentorStudentLookUpRow = MentorStudentLookupRow(
                    mentorId = studentPostPayload.assignedMentors.id!!,
                    studentId = userId,
                    statusCode = 100
                )
                mentorStudentLookupMapper.insertMentorStudentLookup(mentorStudentLookUpRow)
                val assignedMentors = mentorMapper.selectAssignedMentor(userId)
                return StudentDataRelation(
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
                    assignedMentors = assignedMentors,
                    courseTrack = courseTrackValueRow.value,
                    mentorshipOption = mentorshipOptionValueRow.value
                )
            }else{
                return StudentDataRelation(
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
                    assignedMentors = mutableListOf(),
                    courseTrack = courseTrackValueRow.value,
                    mentorshipOption = mentorshipOptionValueRow.value
                )
            }
        } else {
            throw Exception("Invalid courseTrack or mentorshipOption submitted")
        }
    }
    /**
     * Assign a mentor to a student
     */
    fun assignMentor(assignMentorPayload: AssignMentorPayload): StudentDataRelation{
        // create mentorStudentLookup row to insert
        val mentorStudentLookupRow = MentorStudentLookupRow(
            mentorId = assignMentorPayload.mentorId,
            studentId = assignMentorPayload.studentId,
            statusCode = 100
        )
        // insert mentor pairing
        mentorStudentLookupMapper.insertMentorStudentLookup(mentorStudentLookupRow)
        // get student data to return
        val someStudentData = studentMapper.selectStudentById(assignMentorPayload.studentId)
        val mentorData = mentorMapper.selectAssignedMentor(assignMentorPayload.studentId)
        val courseTrack = userConfigValueMapper.selectStudentCourseTrack(assignMentorPayload.studentId)
        val mentorshipOption = userConfigValueMapper.selectStudentMentorShipOption(assignMentorPayload.studentId)
        return StudentDataRelation(
            id = assignMentorPayload.studentId,
            firstName = someStudentData.firstName,
            lastName = someStudentData.lastName,
            roleCode = someStudentData.roleCode,
            statusCode = someStudentData.statusCode,
            flag = someStudentData.flag,
            bio = someStudentData.bio,
            location = someStudentData.location,
            email = someStudentData.email,
            telephone = someStudentData.telephone,
            forumUsername = someStudentData.forumUsername,
            slackUsername = someStudentData.slackUsername,
            assignedMentors = mentorData,
            courseTrack = courseTrack.courseTrack,
            mentorshipOption = mentorshipOption.mentorshipOption
        )
    }
    /**
     * Update student
     */
    fun updateStudent(userUpdatePayload: UserUpdatePayload, studentId: Int): StudentDataRelation{
        println(userUpdatePayload)
        // check for update fields in payload
        if (userUpdatePayload.flag != null){
            // update user flag
            userMapper.updateFlag(userId=studentId, userUpdatePayload.flag)
        }
        if (userUpdatePayload.bio != null){
            // update user bio
            userMapper.updateBio(userId=studentId, userUpdatePayload.bio)
        }
        if (userUpdatePayload.courseTrack != null){
            // update courseTrack
            userConfigValueMapper.updateCourseTrackValue(userId=studentId, userUpdatePayload.courseTrack)
        }
        if (userUpdatePayload.mentorshipOption != null){
            // update mentorshipOption
            userConfigValueMapper.updateMentorshipOptionValue(userId =studentId, userUpdatePayload.mentorshipOption)
        }

        // return updated student object
        val mentorData = mentorMapper.selectAssignedMentor(userUpdatePayload.userId)
        val courseTrack = userConfigValueMapper.selectStudentCourseTrack(userUpdatePayload.userId)
        val mentorshipOption = userConfigValueMapper.selectStudentMentorShipOption(userUpdatePayload.userId)
        val someStudentData = studentMapper.selectStudentById(userUpdatePayload.userId)
        return StudentDataRelation(
            id = userUpdatePayload.userId,
            firstName = someStudentData.firstName,
            lastName = someStudentData.lastName,
            roleCode = someStudentData.roleCode,
            statusCode = someStudentData.statusCode,
            flag = someStudentData.flag,
            bio = someStudentData.bio,
            location = someStudentData.location,
            email = someStudentData.email,
            telephone = someStudentData.telephone,
            forumUsername = someStudentData.forumUsername,
            slackUsername = someStudentData.slackUsername,
            assignedMentors = mentorData,
            courseTrack = courseTrack.courseTrack,
            mentorshipOption = mentorshipOption.mentorshipOption
        )
    }
}

