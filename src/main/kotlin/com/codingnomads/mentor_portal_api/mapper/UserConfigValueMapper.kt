package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.business.CourseTrackData
import com.codingnomads.mentor_portal_api.entity.business.MaxStudentData
import com.codingnomads.mentor_portal_api.entity.business.ProficienciesData
import com.codingnomads.mentor_portal_api.entity.business.UserConfigData
import com.codingnomads.mentor_portal_api.entity.data.ConfigValueRow
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserConfigValueMapper {
    /**
     * Select ALL student userConfigValues
     */
    @Select(SELECT_ALL_STUDENT_VALUES_STATEMENT)
    fun selectAllStudentValues(): List<UserConfigData>

    /**
     * Select ALL mentor userConfigValues
     */
    @Select(SELECT_ALL_MENTOR_VALUES_STATEMENT)
    fun selectAllMentorValues(): List<UserConfigData>

    /**
     * Select mentor's maxStudents value
     */
    @Select(SELECT_MAX_STUDENT_VALUE_STATEMENT)
    fun selectMaxStudentValue(mentorId: Int): MaxStudentData

    /**
     * Select mentor's proficiencies
     */
    @Select(SELECT_PROFICIENCIES_STATEMENT)
    fun selectProficienciesValue(mentorId: Int): List<ProficienciesData>

    /**
     * Select a student's courseTrack
     */
    @Select(SELECT_COURSE_TRACK_STATEMENT)
    fun selectStudentCourseTrack(studentId: Int): CourseTrackData

    @Insert(INSERT_CONFIG_VALUE_STATEMENT + INSERT_CONFIG_VALUE_VALUES)
    fun insertConfigValue(configValueRow: ConfigValueRow)

    companion object {
        const val SELECT_ALL_STUDENT_VALUES_STATEMENT =
            """
                SELECT DISTINCT
                user_config_value.id,
                user_config_value.user_id,
                user_config_value.option_id,
                user_config_value.value
                FROM user_config_value
                JOIN user on user.id = user_config_value.user_id
                WHERE user.role_code = 20
            """
        const val SELECT_ALL_MENTOR_VALUES_STATEMENT =
            """
                SELECT DISTINCT
                user_config_value.id,
                user_config_value.user_id,
                user_config_value.option_id,
                user_config_value.value
                FROM user_config_value
                JOIN user on user.id = user_config_value.user_id
                WHERE user.role_code = 10 
            """
        const val SELECT_MAX_STUDENT_VALUE_STATEMENT =
            """
                SELECT DISTINCT
                user_config_value.option_id,
                user_config_value.user_id,
                user_config_value.value
                FROM user_config_value
                JOIN user on user_config_value.user_id = user.id
                JOIN user_config_option on user_config_value.option_id = user_config_option.id
                WHERE user.id = #{mentorId} and user_config_value.option_id = 1
            """
        const val SELECT_PROFICIENCIES_STATEMENT =
            """
                SELECT DISTINCT
                user_config_value.option_id,
                user_config_value.user_id,
                user_config_value.value
                FROM user_config_value
                JOIN user on user_config_value.user_id = user.id
                JOIN user_config_option on user_config_value.option_id = user_config_option.id
                WHERE user.id = #{mentorId} and user_config_value.option_id = 2
            """
        const val SELECT_COURSE_TRACK_STATEMENT =
            """
                SELECT DISTINCT
                user_config_value.option_id,
                user_config_value.user_id,
                user_config_value.value
                FROM user_config_value
                JOIN user on user_config_value.user_id = user.id
                JOIN user_config_option on user_config_value.option_id = user_config_option.id
                WHERE user.id = #{studentId} and user_config_value.option_id = 3
            """
        const val INSERT_CONFIG_VALUE_STATEMENT =
            """
                INSERT INTO user_config_value
                (
                    option_id,
                    user_id,
                    value
                )
            """

        const val INSERT_CONFIG_VALUE_VALUES =
            """
                VALUES
                (
                    #{optionId},
                    #{userId},
                    #{value}
                )
            """
    }
}