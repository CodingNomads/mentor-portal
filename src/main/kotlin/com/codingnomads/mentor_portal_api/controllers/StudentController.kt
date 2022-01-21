package com.codingnomads.mentor_portal_api.controllers

import com.codingnomads.mentor_portal_api.entities.business.Student
import com.codingnomads.mentor_portal_api.handlers.StudentHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:5000"])
@RestController
@RequestMapping("/api")
class StudentController (@Autowired private val studentHandler: StudentHandler) {
    /**
     * Get all students
     */
    @GetMapping("/students")
    fun getStudents(): List<Student> = studentHandler.getStudents()

    /**
     * Get student by Id
     */
    @GetMapping("/student/{studentId}")
    fun getStudentById(@PathVariable studentId: Int): Student = studentHandler.getStudentById(studentId)
}