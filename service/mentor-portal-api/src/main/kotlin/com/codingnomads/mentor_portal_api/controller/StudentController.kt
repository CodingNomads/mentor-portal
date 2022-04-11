package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.AssignMentorPayload
import com.codingnomads.mentor_portal_api.entity.business.StudentDataRelation
import com.codingnomads.mentor_portal_api.entity.business.StudentPostPayload
import com.codingnomads.mentor_portal_api.entity.business.UserUpdatePayload
import com.codingnomads.mentor_portal_api.handler.StudentHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class StudentController (@Autowired private val studentHandler: StudentHandler) {
    /**
     * Get all students
     */
    @GetMapping("/students")
    fun getStudents(): List<StudentDataRelation> = studentHandler.getStudents()

    /**
     * Get student by Id
     */
    @GetMapping("/students/{studentId}")
    fun getStudentById(@PathVariable studentId: Int) = studentHandler.getStudentById(studentId)
    /**
     * Create a new student
     */
    @PostMapping("/students")
    fun createStudent(@RequestBody payload: StudentPostPayload) = studentHandler.createStudent(payload)
    /**
     * Assign a mentor to a student
     */
    @PostMapping("/assignMentor")
    fun assignMentor(@RequestBody payload: AssignMentorPayload) = studentHandler.assignMentor(payload)
    /**
     * Update flag status for students
     */
    @PutMapping("/students/{studentId}")
    fun updateStudent(@RequestBody payload: UserUpdatePayload,
                      @PathVariable studentId: Int) = studentHandler.updateStudent(payload, studentId)
}