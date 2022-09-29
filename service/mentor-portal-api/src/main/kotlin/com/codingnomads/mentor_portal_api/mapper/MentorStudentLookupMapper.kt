package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.business.MentorshipData
import com.codingnomads.mentor_portal_api.entity.data.MentorStudentLookupRow
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MentorStudentLookupMapper {
    /**
     * Select mentor_student_lookup data
     */
    @Select(MENTOR_STUDENT_LOOKUP_STATEMENT)
    fun selectMentorships(): List<MentorshipData>

    /**
     * Select mentor_student_lookup date for single student
     */
    @Select(MENTOR_STUDENT_LOOKUP_BY_STUDENT)
    fun selectMentorshipByStudent(studentId: Int): List<MentorshipData>

    /**
     * Insert mentor_student_lookup data
     */
    @Insert(INSERT_MENTOR_STUDENT_LOOKUP_STATEMENT + INSERT_MENTOR_STUDENT_LOOKUP_VALUES)
    fun insertMentorStudentLookup(mentorStudentLookupRow: MentorStudentLookupRow)

    companion object{
        const val MENTOR_STUDENT_LOOKUP_STATEMENT =
            """
                SELECT
                id,
                mentor_id,
                student_id,
                status_code,
                start_date,
                end_date
                FROM mentor_student_lookup
            """

        const val MENTOR_STUDENT_LOOKUP_BY_STUDENT =
            """
                SELECT
                id,
                mentor_id,
                student_id,
                status_code,
                start_date,
                end_date
                FROM mentor_student_lookup
                WHERE student_id = #{studentId}
            """

        const val INSERT_MENTOR_STUDENT_LOOKUP_STATEMENT =
            """
                INSERT INTO mentor_student_lookup
                (
                mentor_id,
                student_id,
                status_code
                )
            """
        const val INSERT_MENTOR_STUDENT_LOOKUP_VALUES =
            "VALUES (#{mentorId}, #{studentId}, 100)"
    }
}