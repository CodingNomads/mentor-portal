package com.codingnomads.mentor_portal_api.mappers

import com.codingnomads.mentor_portal_api.entities.business.Student
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
    fun selectStudents(): List<Student>

    /**
     * Get student by id
     */
    @Select(SELECT_STUDENT_STATEMENT)
    fun selectStudentById(id: Int): Student

    companion object {
        const val SELECT_STUDENTS_STATEMENT =
            """
                SELECT DISTINCT 
                user.id,
                first_name,
                last_name,
                role_code,
                user.status_code,
                contact.email,
                contact.telephone
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
                contact.telephone
                FROM user
                JOIN mentor_student_lookup on student_id = user.id
                JOIN contact on user_id = user.id
                WHERE role_code = 20 and user.status_code = 100 and user.id = #{value}
            """
    }
}