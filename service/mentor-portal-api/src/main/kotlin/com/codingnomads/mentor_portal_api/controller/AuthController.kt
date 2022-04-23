package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.MentorPostPayload
import com.codingnomads.mentor_portal_api.entity.business.SuccessfulLoginResponse
import com.codingnomads.mentor_portal_api.entity.business.UserLoginPayload
import com.codingnomads.mentor_portal_api.entity.business.UserSecurityDTO
import com.codingnomads.mentor_portal_api.handler.AuthenticationHandler
import com.codingnomads.mentor_portal_api.handler.UserHandler
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class AuthController (@Autowired private val authenticationHandler: AuthenticationHandler){
    @PostMapping("/authenticate")
    fun authenticate(@RequestBody payload: UserLoginPayload): HttpEntity<SuccessfulLoginResponse> {
        val userSecurityDTO = authenticationHandler.loginUser(payload)




        return HttpEntity(
            SuccessfulLoginResponse(
            "",
            "",
            true
        )
        )
    }
}