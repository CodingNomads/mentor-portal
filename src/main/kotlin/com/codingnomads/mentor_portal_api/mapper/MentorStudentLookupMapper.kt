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
     * Insert mentor_student_lookup data
     */
    @Insert(INSERT_MENTOR_STUDENT_LOOKUP_STATEMENT + INSERT_MENTOR_STUDENT_LOOKUP_VALUES)
    fun insertMentorStudentLookup(mentorStudentLookupRow: MentorStudentLookupRow)

    companion object{
        const val MENTOR_STUDENT_LOOKUP_STATEMENT =
            """
                SELECT
                *
                FROM mentor_student_lookup
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