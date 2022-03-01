package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.business.Student
import com.codingnomads.mentor_portal_api.entity.business.StudentData
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 * Mapper for Students
 */

@Mapper
interface StudentMapper {
    /**
     * Select all students
     */
    @Select(SELECT_STUDENTS_STATEMENT)
    fun selectStudents(): List<StudentData>

    /**
     * Get student by id
     */
    @Select(SELECT_STUDENT_STATEMENT)
    fun selectStudentById(id: Int): StudentData

    /**
     * Select students assigned to a mentor
     */
    @Select(SELECT_ASSIGNED_STUDENTS)
    fun selectAssignedStudents(id: Int): List<StudentData>

    companion object {
        const val SELECT_STUDENTS_STATEMENT =
            """
                SELECT DISTINCT 
                user.id,
                user.first_name,
                user.last_name,
                user.role_code,
                user.status_code,
                contact.email,
                contact.telephone,
                contact.forum_username,
                contact.slack_username
                FROM user
                JOIN mentor_student_lookup on mentor_student_lookup.student_id = user.id
                JOIN contact on user_id = user.id
                WHERE role_code = 20 and user.status_code = 100
            """

        const val SELECT_STUDENT_STATEMENT =
            """
                SELECT DISTINCT 
                user.id,
                user.first_name,
                user.last_name,
                user.role_code,
                user.status_code, 
                contact.email,
                contact.telephone,
                contact.forum_username,
                contact.slack_username 
                FROM user
                JOIN mentor_student_lookup on student_id = user.id
                JOIN contact on user_id = user.id
                WHERE role_code = 20 and user.status_code = 100 and user.id = #{value}
            """
        const val SELECT_ASSIGNED_STUDENTS =
            """
                SELECT DISTINCT
                user.id,
                user.first_name,
                user.last_name,
                user.role_code,
                user.status_code,
                contact.email,
                contact.telephone,
                contact.forum_username,
                contact.slack_username
                FROM user
                JOIN contact on user.id = contact.user_id
                JOIN mentor_student_lookup on user.id = mentor_student_lookup.student_id
                WHERE user.role_code = 20 and mentor_student_lookup.mentor_id = #{value}
            """
    }
}