package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.business.MentorData
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface MentorMapper {
    /**
     * Select all Mentors from database
     */
    @Select(SELECT_MENTORS_STATEMENT)
    fun selectMentors(): List<MentorData>

    /**
     * Select mentor by id
     */
    @Select(SELECT_MENTOR_STATEMENT)
    fun selectMentorById(id: Int): MentorData

    /**
     * Select ALL mentors with students
     */
    @Select(SELECT_ALL_MENTORS_WITH_STUDENTS)
    fun selectAllMentorsWithStudents(): List<MentorData>

    /**
     * Select mentors assigned to a student
     */
    @Select(SELECT_ASSIGNED_MENTOR)
    fun selectAssignedMentor(studentId: Int):List<MentorData>


    companion object {
        const val SELECT_MENTORS_STATEMENT =
            """
                SELECT DISTINCT
                user.id,
                user.email
                user.first_name,
                user.last_name,
                user.role_code,
                user.status_code,
                user.flag,
                user.bio,
                contact.location,
                contact.telephone,
                contact.forum_username,
                contact.slack_username
                FROM user
                JOIN contact on user.id = contact.user_id
                WHERE user.role_code = 10
            """
        const val SELECT_MENTOR_STATEMENT =
            """
                SELECT DISTINCT
                user.id,
                user.email
                user.first_name,
                user.last_name,
                user.role_code,
                user.status_code,
                user.flag,
                user.bio,
                contact.location,
                contact.telephone,
                contact.forum_username,
                contact.slack_username
                FROM user_config_value
                JOIN user on user_config_value.user_id = user.id
                JOIN contact on user.id = contact.user_id
                WHERE role_code = 10 and user.id = #{value}
            """
        const val SELECT_ALL_MENTORS_WITH_STUDENTS =
            """
                SELECT DISTINCT
                user.id,
                user.email,
                user.first_name,
                user.last_name,
                user.role_code,
                user.status_code,
                user.flag,
                user.bio,
                contact.location,
                contact.telephone,
                contact.forum_username,
                contact.slack_username
                FROM user_config_value
                JOIN user on user_config_value.user_id = user.id
                JOIN mentor_student_lookup on user.id = mentor_student_lookup.mentor_id
                JOIN contact on user.id = contact.user_id
                WHERE role_code = 10
            """
        const val SELECT_ASSIGNED_MENTOR =
            """
                SELECT DISTINCT
                user.id,
                user.email,
                user.first_name,
                user.last_name,
                user.role_code,
                user.status_code,
                user.flag,
                user.bio,
                contact.location,
                contact.telephone,
                contact.forum_username,
                contact.slack_username
                FROM user_config_value
                JOIN user on user_config_value.user_id = user.id
                JOIN mentor_student_lookup on user.id = mentor_student_lookup.mentor_id
                JOIN contact on user.id = contact.user_id
                WHERE role_code = 10 and student_id = #{value}
            """
        const val INSERT_MENTOR_STATEMENT =
            """
            INSERT INTO user 
            (
            email
            first_name, 
            last_name, 
            status_code, 
            role_code, 
            timezone_offset,
            flag,
            bio,
            )
            """
        const val INSERT_MENTOR_VALUES = "VALUES (#{email}, #{firstName}, #{lastName}, #{statusCode}, #{roleCode}, #{timezoneOffset}, #{flag}, #{bio})"
    }
}