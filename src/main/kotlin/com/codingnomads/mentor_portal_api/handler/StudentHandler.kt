package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.mapper.StudentMapper
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

    /**
     * Get student by id
     */
    fun getStudentById(studentId: Int) = studentMapper.selectStudentById(studentId)
}

