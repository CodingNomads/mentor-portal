package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.SupportLogPayload
import com.codingnomads.mentor_portal_api.handler.SupportLogHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class SupportLogController(@Autowired private val supportLogHandler: SupportLogHandler) {
    /**
     * post endpoint for supportLog
     */
    @PostMapping("/supportLog")
    fun createSupportLog(@RequestBody payload: SupportLogPayload) = supportLogHandler.createSupportLog(payload)
}