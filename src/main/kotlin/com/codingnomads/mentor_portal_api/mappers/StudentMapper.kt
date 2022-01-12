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

    companion object {
        const val SELECT_STUDENTS_STATEMENT =
            """
                SELECT DISTINCT 
                user.id,
                first_name,
                last_name,
                role_code,
                user.status_code 
                FROM user
                JOIN mentor_student_lookup on student_id = user.id
                JOIN contact on user_id = user.id
                WHERE role_code = 20 and user.status_code = 100
            """
    }
}