package com.codingnomads.mentor_portal_api.handlers

import com.codingnomads.mentor_portal_api.entities.business.Student
import com.codingnomads.mentor_portal_api.mappers.StudentMapper
import org.springframework.stereotype.Component

/**
 * Handles Students
 */
@Component
class StudentHandler(private val studentMapper: StudentMapper) {
    /**
     * Get all Students
     */
    fun getStudents() = studentMapper.selectStudents()
}

