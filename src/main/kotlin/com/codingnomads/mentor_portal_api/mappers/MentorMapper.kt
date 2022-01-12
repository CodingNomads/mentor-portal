package com.codingnomads.mentor_portal_api.mappers

import com.codingnomads.mentor_portal_api.entities.data.MentorRow
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface MentorMapper {
    /**
     * Select all Mentors from database
     */
    @Select(SELECT_MENTORS_STATEMENT)
    fun selectMentors(): MentorRow

    companion object {
        const val SELECT_MENTORS_STATEMENT =
            """
                SELECT
                id,
                first_name,
                last_name,
                role_code,
                status_code 
                FROM user,
                JOIN roles on user.id = user.id
                JOIN on mentor_student_lookup
                JOIN contact on user.id = user.id
                WHERE role_code = 10
            """
    }
}