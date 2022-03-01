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
     * Select mentors assigned to a student
     */
    @Select(SELECT_ASSIGNED_MENTOR)
    fun selectAssignedMentor(id: Int):List<MentorData>


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
                contact.telephone,
                contact.forum_username,
                contact.slack_username,
                user_config_option.option_name,
                user_config_value.value
                FROM user_config_option
                JOIN user_config_value on user_config_option.id = user_config_value.option_id
                JOIN user on user_config_value.user_id = user.id
                JOIN mentor_student_lookup on user.id = mentor_student_lookup.mentor_id
                JOIN contact on user.id = contact.user_id
                WHERE user.role_code = 10
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
                contact.telephone,
                contact.forum_username,
                contact.slack_username
                FROM user
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
                contact.telephone,
                contact.forum_username,
                contact.slack_username,
                user_config_option.option_name,
                user_config_value.value
                FROM user_config_option
                JOIN user_config_value on user_config_option.id = user_config_value.option_id
                JOIN user on user_config_value.user_id = user.id
                JOIN mentor_student_lookup on user.id = mentor_student_lookup.mentor_id
                JOIN contact on user.id = contact.user_id
                WHERE role_code = 10 and user.status_code = 100 and user.id = #{value}
            """
        const val SELECT_ASSIGNED_MENTOR =
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
                contact.slack_username,
                user_config_option.option_name,
                user_config_value.value
                FROM user_config_option
                JOIN user_config_value on user_config_option.id = user_config_value.option_id
                JOIN user on user_config_value.user_id = user.id
                JOIN mentor_student_lookup on user.id = mentor_student_lookup.mentor_id
                JOIN contact on user.id = contact.user_id
                WHERE role_code = 10 and user.status_code = 100 and student_id = #{value}
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