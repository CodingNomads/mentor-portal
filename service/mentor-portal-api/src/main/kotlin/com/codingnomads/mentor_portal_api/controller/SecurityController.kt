package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.ResetPasswordPayload
import com.codingnomads.mentor_portal_api.handler.SecurityHandler
import com.codingnomads.mentor_portal_api.security.Headers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SecurityController(@Autowired private val securityHandler: SecurityHandler) {

    @PostMapping("/reset-password")
    fun resetPassword(
        resetPasswordPayload: ResetPasswordPayload,
        @RequestHeader(Headers.AUTHORIZATION) jwtToken: String) {
        securityHandler.resetPasswordByEmail(resetPasswordPayload, jwtToken)
    }

}