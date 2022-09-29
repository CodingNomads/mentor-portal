package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.business.*
import com.codingnomads.mentor_portal_api.entity.data.ConfigValueRow
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Update

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

    /**
     * Select a students mentorshipOption
     */
    @Select(SELECT_MENTORSHIP_OPTION_STATEMENT)
    fun selectStudentMentorShipOption(studentId: Int): MentorshipOptionData

    /**
     * Select a student program start date
     */
    @Select(SELECT_PROGRAM_START)
    fun selectProgramStart(studentId: Int): UserConfigData

    /**
     * Select a student program end date
     */
    @Select(SELECT_PROGRAM_END)
    fun selectProgramEnd(studentId: Int): UserConfigData

    /**
     * create student config values: courseTrack and mentorshipOption
     */
    @Insert(INSERT_CONFIG_VALUE_STATEMENT + INSERT_CONFIG_VALUE_VALUES)
    fun insertConfigValue(configValueRow: ConfigValueRow)

    /**
     * Update student config value: courseTrack
     */
    @Update(UPDATE_COURSETRACK_STATEMENT)
    fun updateCourseTrackValue(userId: Int, courseTrack: String): Int

    /**
     * Update student config value: mentorshipOption
     */
    @Update(UPDATE_MENTORSHIP_OPTION_STATEMENT)
    fun updateMentorshipOptionValue(userId: Int, mentorshipOption: String): Int

    /**
     * Update student config value: programStart
     */
    @Update(UPDATE_PROGRAM_START_STATEMENT)
    fun updateProgramStart(userId: Int, programStart: String): Int

    /**
     * Update student config value: programEnd
     */
    @Update(UPDATE_PROGRAM_END_STATEMENT)
    fun updateProgramEnd(userId: Int, programEnd: String): Int


    /**
     * Create student program start
     */
    @Insert(INSERT_PROGRAM_START_STATEMENT + INSERT_PROGRAM_START_VALUES)
    fun insertProgramStart(studentId: Int, optionId: Int, programStart: String): Int

    /**
     * Create student program end
     */
    @Insert(INSERT_PROGRAM_END_STATEMENT + INSERT_PROGRAM_END_VALUES)
    fun insertProgramEnd(studentId: Int, optionId: Int, programEnd: String): Int

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
        const val SELECT_MENTORSHIP_OPTION_STATEMENT =
            """
                SELECT DISTINCT
                user_config_value.option_id,
                user_config_value.user_id,
                user_config_value.value
                FROM user_config_value
                JOIN user on user_config_value.user_id = user.id
                JOIN user_config_option on user_config_value.option_id = user_config_option.id
                WHERE user.id = #{studentId} and user_config_value.option_id = 4
            """
        const val SELECT_PROGRAM_START =
            """
                SELECT DISTINCT
                user_config_value.id,
                user_config_value.user_id,
                user_config_value.option_id,
                user_config_value.value
                FROM user_config_value
                JOIN user on user_config_value.user_id = user.id
                WHERE user_config_value.user_id = #{studentId} and user_config_value.option_id = 5
            """
        const val SELECT_PROGRAM_END =
            """
                SELECT DISTINCT
                user_config_value.id,
                user_config_value.user_id,
                user_config_value.option_id,
                user_config_value.value
                FROM user_config_value
                JOIN user on user_config_value.user_id = user.id
                WHERE user_config_value.user_id = #{studentId} and user_config_value.option_id = 6
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
        const val UPDATE_COURSETRACK_STATEMENT =
            """
               UPDATE user_config_value
               SET user_config_value.value = #{courseTrack}
               WHERE user_config_value.user_id = #{userId} and user_config_value.option_id = 3
            """
        const val UPDATE_MENTORSHIP_OPTION_STATEMENT =
            """
                UPDATE user_config_value
                SET user_config_value.value = #{mentorshipOption}
                WHERE user_config_value.user_id = #{userId} and user_config_value.option_id = 4
            """

        const val INSERT_PROGRAM_START_STATEMENT =
            """
                INSERT into user_config_value 
                (
                user_id,
                option_id,
                value
                )
                
            """

        const val INSERT_PROGRAM_START_VALUES =
            """
                VALUES
                (
                #{studentId},
                #{optionId},
                #{programStart}
                )
            """

        const val INSERT_PROGRAM_END_STATEMENT =
            """
                INSERT into user_config_value
                (
                user_id,
                option_id,
                value
                )
            """

        const val INSERT_PROGRAM_END_VALUES =
            """
                VALUES
                (
                #{studentId},
                #{optionId},
                #{programEnd}
                )
            """

        const val UPDATE_PROGRAM_START_STATEMENT =
            """
                UPDATE user_config_value
                SET user_config_value.value = #{programStart}
                WHERE user_config_value.user_id = #{userId} and user_config_value.option_id = 5
            """
        const val UPDATE_PROGRAM_END_STATEMENT =
            """
                UPDATE user_config_value
                SET user_config_value.value = #{programEnd}
                WHERE user_config_value.user_id = #{userId} and user_config_value.option_id = 6
            """
    }
}