package com.codingnomads.mentor_portal_api.mapper

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MentorStudentLookupMapper {
    /**
     * Insert mentor_student_lookup data
     */
    @Insert()
    fun insertMentorStudentLookup()
}