package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.MentorshipData
import com.codingnomads.mentor_portal_api.handler.MentorStudentLookupHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class MentorStudentLookupController (@Autowired private val mentorStudentLookupHandler: MentorStudentLookupHandler) {
    /**
     * Get mentorStudentLookup data by studentId
     */
    @GetMapping("/msl/{studentId}")
    fun getMentorStudentLookupData(@PathVariable studentId: Int): List<MentorshipData> = mentorStudentLookupHandler.getMentorStudentLookupData(studentId)
}