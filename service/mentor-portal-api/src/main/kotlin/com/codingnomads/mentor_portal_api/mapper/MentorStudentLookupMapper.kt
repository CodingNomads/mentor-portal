package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.business.MentorshipData
import com.codingnomads.mentor_portal_api.entity.data.MentorStudentLookupRow
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Update

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
    /**
     * Update mentorStudentLookup code(status)
     */
    @Update(UPDATE_MENTOR_STUDENT_LOOKUP_STATUS)
    fun updateStatus(id: Int, statusCode: Int)

    companion object{
        const val MENTOR_STUDENT_LOOKUP_STATEMENT =
            """
                SELECT 
                mentor_student_lookup.id,
                mentor_student_lookup.mentor_Id, 
                mentor_student_lookup.student_id,
                mentor_student_lookup.status_code, 
                status.description
                FROM mentor_student_lookup
                JOIN status ON mentor_student_lookup.status_code = status.code
            """

        const val MENTOR_STUDENT_LOOKUP_BY_STUDENT =
            """
                SELECT DISTINCT
                mentor_student_lookup.id,
                mentor_student_lookup.mentor_Id, 
                mentor_student_lookup.student_id,
                mentor_student_lookup.status_code, 
                status.description
                FROM mentor_student_lookup
                JOIN status ON mentor_student_lookup.status_code = status.code
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

        const val UPDATE_MENTOR_STUDENT_LOOKUP_STATUS =
            """
                UPDATE mentor_student_lookup
                SET mentor_student_lookup.status_code = #{statusCode}
                WHERE id = #{id}
            """
    }
}