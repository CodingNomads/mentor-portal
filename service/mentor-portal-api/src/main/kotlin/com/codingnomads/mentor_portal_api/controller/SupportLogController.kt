package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.SupportLog
import com.codingnomads.mentor_portal_api.entity.business.SupportLogFlagPayload
import com.codingnomads.mentor_portal_api.entity.business.SupportLogPayload
import com.codingnomads.mentor_portal_api.handler.SupportLogHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class SupportLogController(@Autowired private val supportLogHandler: SupportLogHandler) {
    /**
     * GET all supportlogs
     */
    @GetMapping("/supportlogs")
    fun getAllSupportLogs(): List<SupportLog> = supportLogHandler.getAllSupportLogs()
    /**
     * GET all of a user's support logs
     */
    @GetMapping("/{userId}/supportLogs")
    fun getStudentSupportLogs(@PathVariable userId: Int): List<SupportLog> = supportLogHandler.getSupportLogs(userId)
    /**
     * GET single support log for user
     */
    @GetMapping("/{userId}/supportLogs/{supportLogId}")
    fun getSingleStudentSupportLog(
        @PathVariable userId: Int,
        @PathVariable supportLogId: Int
    ): SupportLog = supportLogHandler.getSingleSupportLog(userId, supportLogId)
    /**
     * PUT endpoint for updating flag of single support log
     */
    @PreAuthorize("hasAuthority('student:write')")
    @PutMapping("/{userId}/supportLogs/{supportLogId}")
    fun updateSupportLog(@RequestBody payload: SupportLogFlagPayload,
                             @PathVariable userId: Int,
                             @PathVariable supportLogId: Int) = supportLogHandler.updateSupportLog(payload, userId, supportLogId)
    /**
     * POST endpoint for creating new support log
     */
    @PostMapping("/supportLog")
    fun createSupportLog(@RequestBody payload: SupportLogPayload) = supportLogHandler.createSupportLog(payload)
}