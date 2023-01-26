package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.MentorStudentLookupUpdate
import com.codingnomads.mentor_portal_api.entity.business.MentorshipData
import com.codingnomads.mentor_portal_api.handler.MentorStudentLookupHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class MentorStudentLookupController (@Autowired private val mentorStudentLookupHandler: MentorStudentLookupHandler) {
    /**
     * Get mentorStudentLookup data by studentId
     */
    @GetMapping("/msl/{studentId}")
    fun getMentorStudentLookupData(@PathVariable studentId: Int): List<MentorshipData> = mentorStudentLookupHandler.getMentorStudentLookupData(studentId)
    /**
     * Update mentorStudentLookup status
     */
    @PreAuthorize("hasAuthority('admin:write')")
    @PutMapping("/msl/{studentId}")
    fun updateStatus(@RequestBody payload: MentorStudentLookupUpdate,
                     @PathVariable studentId: Int) = mentorStudentLookupHandler.updateStatus(payload, studentId)
}