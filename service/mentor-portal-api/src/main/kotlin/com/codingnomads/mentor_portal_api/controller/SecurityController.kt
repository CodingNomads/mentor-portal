package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.ResetPasswordPayload
import com.codingnomads.mentor_portal_api.handler.SecurityHandler
import com.codingnomads.mentor_portal_api.security.Headers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class SecurityController(@Autowired private val securityHandler: SecurityHandler) {

    @PutMapping("/reset-password")
    fun resetPassword(
        @RequestHeader(Headers.AUTHORIZATION) jwtToken: String,
        @RequestBody payload: ResetPasswordPayload) {
        securityHandler.resetPasswordByEmail(jwtToken, payload)
    }

}