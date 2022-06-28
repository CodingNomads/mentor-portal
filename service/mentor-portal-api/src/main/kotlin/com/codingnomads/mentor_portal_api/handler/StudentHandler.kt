package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.*
import com.codingnomads.mentor_portal_api.entity.data.*
import com.codingnomads.mentor_portal_api.entity.enum.CourseOption
import com.codingnomads.mentor_portal_api.entity.enum.MentorshipOption
import com.codingnomads.mentor_portal_api.mapper.*
import org.springframework.stereotype.Component
import kotlin.reflect.typeOf

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
        // empty list to add constructed students to
        val studentList: MutableList<StudentDataRelation> = mutableListOf()
        // base student data
        val studentDataList = studentMapper.selectStudents()
        // mentorship and mentors data
        val mentorshipDataList = mentorStudentLookupMapper.selectMentorships()
        val mentorsList = mentorMapper.selectAllMentorsWithStudents()
        // config values for students
        val studentsConfigValuesList = userConfigValueMapper.selectAllStudentValues()
        // maps
        val mentorsWithStudentsByIdMap: Map<Int, MentorData> = mentorsList.associateBy { it.id!! }
        val mentorshipPairMap: Map<Int, MentorshipData> = mentorshipDataList.associateBy { it.studentId }

        for (student in studentDataList){
            // key for accessing dictionary values
            val studentId = student.id
            println("StudentId: ${student.id} \nStudent Name: ${student.firstName}")
            println(studentsConfigValuesList)

            // courseTrack config
            val filteredStudentCourseTrackList = studentsConfigValuesList.filter { it.optionId == 3 }
            println(filteredStudentCourseTrackList)
            val studentCourseTrackMap: Map<Int, UserConfigData> = filteredStudentCourseTrackList.associateBy { it.userId }
            println(studentCourseTrackMap)

            // mentorshipOption config
            val mentorshipOptionList = studentsConfigValuesList.filter { it.optionId == 4 }
            println(mentorshipOptionList)
            val mentorshipOptionMap: Map<Int, UserConfigData> = mentorshipOptionList.associateBy { it.userId }
            println(mentorshipOptionMap)
            val filteredMentorshipPair = mentorshipDataList.filter { it.studentId == student.id }
            val filteredMentors = filteredMentorshipPair.map { mentor -> mentorsWithStudentsByIdMap[mentor.mentorId]!! }
            // if students have a mentor they also have a mentorshipOption
            if (mentorshipPairMap[student.id] != null && mentorshipOptionList.isNotEmpty()){

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
                    courseTrack = studentCourseTrackMap[studentId]!!.value,
                    mentorshipOption = mentorshipOptionMap[studentId]!!.value
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
                    courseTrack = studentCourseTrackMap[studentId]!!.value,
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
        val studentCourseTrack = userConfigValueMapper.selectStudentCourseTrack(studentId)
        val mentorData = mentorMapper.selectAssignedMentor(studentId)
        val studentMentorshipOption = userConfigValueMapper.selectStudentMentorShipOption(studentId)

        if (mentorData === null || studentMentorshipOption === null){
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
                assignedMentors = emptyList(),
                supportLog = supportLog,
                courseTrack = studentCourseTrack.courseTrack,
                mentorshipOption = ""
            )
        } else {
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
    }

    /**
     * Create a student
     */
    fun createStudent(studentPostPayload: StudentPostPayload): StudentDataRelation{
        // validate courseTrack sent in studentPostPayload
        val courseTrackLowerCase = studentPostPayload.courseTrack.lowercase()
        val courseTrackVerification = CourseOption.values().filter { it.title == courseTrackLowerCase }
        // if courseTrack is valid
        if (courseTrackVerification.isNotEmpty()){
            // user table fields
            val userRow = UserRow(
                email = studentPostPayload.email,
                firstName = studentPostPayload.firstName,
                lastName = studentPostPayload.lastName,
                roleCode = 20,
                statusCode = 100,
                flag = false,
                bio = studentPostPayload.bio!!,
                timezoneOffset = studentPostPayload.timezoneOffset
            )
            userMapper.insertUser(userRow)
            val userId = userRow.id!!

            // contact table fields
            val contactRow = ContactRow(
                userId = userId,
                telephone = studentPostPayload.telephone,
                location = studentPostPayload.location,
                forumUsername = studentPostPayload.forumUsername!!,
                slackUsername = studentPostPayload.slackUsername!!
            )
            contactMapper.insertContact(contactRow)

            // config data: courseTrack
            val courseTrackOptionRow = userConfigOptionMapper.selectOptionByName("courseTrack")
            val courseTrackValueRow = ConfigValueRow(
                optionId = courseTrackOptionRow.id,
                userId = userId,
                value = studentPostPayload.courseTrack
            )
            userConfigValueMapper.insertConfigValue(courseTrackValueRow)

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
                assignedMentors = emptyList(),
                courseTrack = courseTrackValueRow.value,
                mentorshipOption = ""
            )
        }
        else {
            throw Exception("Invalid courseTrack submitted")
        }
    }
    /**
     * Assign a mentor to a student
     */
    fun assignMentor(assignMentorPayload: AssignMentorPayload): StudentDataRelation{
        // check active status of student
        val studentDataCheck = studentMapper.selectStudentById(assignMentorPayload.studentId)
        if (studentDataCheck.statusCode === 200){
            userMapper.updateStatusCode(assignMentorPayload.studentId, statusCode = 100)
        }

        // create mentorStudentLookup row to insert
        val mentorStudentLookupRow = MentorStudentLookupRow(
            mentorId = assignMentorPayload.mentorId,
            studentId = assignMentorPayload.studentId,
            statusCode = 100
        )
        mentorStudentLookupMapper.insertMentorStudentLookup(mentorStudentLookupRow)

        // create mentorshipOption for student
        val configValueRow = ConfigValueRow(
            optionId = 4,
            userId = assignMentorPayload.studentId,
            value = assignMentorPayload.mentorshipOption
        )
        userConfigValueMapper.insertConfigValue(configValueRow)

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

