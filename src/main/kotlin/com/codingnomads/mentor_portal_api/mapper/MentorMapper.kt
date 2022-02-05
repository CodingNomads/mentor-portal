package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.business.Mentor
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface MentorMapper {
    /**
     * Select all Mentors from database
     */
    @Select(SELECT_MENTORS_STATEMENT)
    fun selectMentors(): List<Mentor>

    /**
     * Select mentor by id
     */
    @Select(SELECT_MENTOR_STATEMENT)
    fun selectMentorById(id: Int): Mentor

    companion object {
        const val SELECT_MENTORS_STATEMENT =
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
                JOIN mentor_student_lookup on mentor_id = user.id
                JOIN contact on user_id = user.id
                WHERE role_code = 10 and user.status_code = 100
            """
        const val SELECT_NEW_MENTORS_STATEMENT =
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
                RIGHT JOIN mentor_student_lookup on mentor_id = user.id
                JOIN contact on user_id = user.id
                WHERE user.role_code = 10 and user.status_code = 100
            """
        const val SELECT_MENTOR_STATEMENT =
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
                JOIN mentor_student_lookup on mentor_id = user.id
                JOIN contact on user_id = user.id
                WHERE role_code = 10 and user.status_code = 100 and user.id = #{value}
            """
        const val INSERT_MENTOR_STATEMENT =
            """
            INSERT INTO user 
            (
            first_name, 
            last_name, 
            status_code, 
            role_code, 
            timezone_offset
            )
            """
        const val INSERT_MENTOR_VALUES = "VALUES (#{firstName}, #{lastName}, #{statusCode}, #{roleCode}, #{timezoneOffset})"
    }
}