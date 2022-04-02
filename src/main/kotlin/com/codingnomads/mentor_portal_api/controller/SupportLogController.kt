package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.SupportLog
import com.codingnomads.mentor_portal_api.entity.business.SupportLogFlagPayload
import com.codingnomads.mentor_portal_api.entity.business.SupportLogPayload
import com.codingnomads.mentor_portal_api.handler.SupportLogHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class SupportLogController(@Autowired private val supportLogHandler: SupportLogHandler) {
    /**
     * GET all of a student's support logs
     */
    @GetMapping("/students/{studentId}/supportLogs")
    fun getSupportLogs(@PathVariable studentId: Int): List<SupportLog> = supportLogHandler.getSupportLogs(studentId)
    /**
     * GET single support log for student
     */
    @GetMapping("/students/{studentId}/supportLogs/{supportLogId}")
    fun getSingleSupportLog(
        @PathVariable studentId: Int,
        @PathVariable supportLogId: Int
    ): SupportLog = supportLogHandler.getSingleSupportLog(studentId, supportLogId)
    /**
     * PUT endpoint for updating flag of single support log
     */
    @PutMapping("/students/{studentId}/supportLogs/{supportLogId}")
    fun updateSupportLogFlag(@RequestBody payload: SupportLogFlagPayload,
                             @PathVariable studentId: Int,
                             @PathVariable supportLogId: Int) = supportLogHandler.updateSupportLogFlag(payload, studentId, supportLogId)
    /**
     * POST endpoint for creating new support log
     */
    @PostMapping("/supportLog")
    fun createSupportLog(@RequestBody payload: SupportLogPayload) = supportLogHandler.createSupportLog(payload)
    /**
     * PUT endpoint for changing flag status of support log
     */
//    @PutMapping("/supportLog/{supportLogId}")
//    fun updateLogFlag(@RequestBody payload: )
}