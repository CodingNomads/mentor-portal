package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.*
import com.codingnomads.mentor_portal_api.entity.data.MentorStudentLookupRow
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
    private val studentMapper: StudentMapper,
    private val supportLogMapper: SupportLogMapper,
    private val userMapper: UserMapper,
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
        // maps
        val mentorsWithStudentsByIdMap: Map<Int, MentorData> = mentorsList.associateBy { it.id!! }
        val mentorshipPairMap: Map<Int, MentorshipData> = mentorshipDataList.associateBy { it.studentId }
        val studentConfigValuesMap: Map<Int, UserConfigData> = studentsConfigValuesList.associateBy { it.userId }

        for (student in studentDataList){
            val filteredMentorshipPair = mentorshipDataList.filter { it.studentId == student.id }
            val filteredMentors = filteredMentorshipPair.filter { true }.map { mentor -> mentorsWithStudentsByIdMap[mentor.mentorId]!! }
            if (mentorshipPairMap[student.id] != null){
                val someStudent = StudentDataRelation(
                    id = student.id!!,
                    firstName = student.firstName,
                    lastName = student.lastName,
                    roleCode = student.roleCode,
                    statusCode = student.statusCode,
                    flag = student.flag,
                    email = student.email,
                    telephone = student.telephone,
                    forumUsername = student.forumUsername,
                    slackUsername = student.slackUsername,
                    assignedMentors = filteredMentors,
                    courseTrack = studentConfigValuesMap[student.id]!!.value
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
                    email = student.email,
                    telephone = student.telephone,
                    forumUsername = student.forumUsername,
                    slackUsername = student.slackUsername,
                    assignedMentors = mutableListOf(),
                    courseTrack = studentConfigValuesMap[student.id]!!.value,
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

        return Student(
            id = studentData.id,
            firstName = studentData.firstName,
            lastName = studentData.lastName,
            roleCode = studentData.roleCode,
            statusCode = studentData.statusCode,
            flag = studentData.flag,
            email = studentData.email,
            telephone = studentData.telephone,
            forumUsername = studentData.forumUsername,
            slackUsername = studentData.slackUsername,
            assignedMentors = mentorData,
            supportLog = supportLog,
            courseTrack = studentCourseTrack.courseTrack
        )
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
        return StudentDataRelation(
            id = assignMentorPayload.studentId,
            firstName = someStudentData.firstName,
            lastName = someStudentData.lastName,
            roleCode = someStudentData.roleCode,
            statusCode = someStudentData.statusCode,
            flag = someStudentData.flag,
            email = someStudentData.email,
            telephone = someStudentData.telephone,
            forumUsername = someStudentData.forumUsername,
            slackUsername = someStudentData.slackUsername,
            assignedMentors = mentorData,
            courseTrack = courseTrack.courseTrack
        )
    }

    /**
     * Update student flag
     */
    fun updateFlag(flagPayload: UserFlagPayload): StudentDataRelation {
        // update flag boolean
        userMapper.updateFlag(flagPayload.flag, flagPayload.userId)
        // return updated student object
        val mentorData = mentorMapper.selectAssignedMentor(flagPayload.userId)
        val courseTrack = userConfigValueMapper.selectStudentCourseTrack(flagPayload.userId)
        val someStudentData = studentMapper.selectStudentById(flagPayload.userId)
        return StudentDataRelation(
            id = flagPayload.userId,
            firstName = someStudentData.firstName,
            lastName = someStudentData.lastName,
            roleCode = someStudentData.roleCode,
            statusCode = someStudentData.statusCode,
            flag = someStudentData.flag,
            email = someStudentData.email,
            telephone = someStudentData.telephone,
            forumUsername = someStudentData.forumUsername,
            slackUsername = someStudentData.slackUsername,
            assignedMentors = mentorData,
            courseTrack = courseTrack.courseTrack
        )
    }
}

