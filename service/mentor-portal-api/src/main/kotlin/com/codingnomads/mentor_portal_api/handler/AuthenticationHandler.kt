package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.UserLoginPayload
import com.codingnomads.mentor_portal_api.entity.business.UserSecurityDTO
import com.codingnomads.mentor_portal_api.mapper.SecurityMapper
import com.codingnomads.mentor_portal_api.security.JwtHandler
import org.springframework.stereotype.Component

@Component
class AuthenticationHandler(
    private val securityMapper: SecurityMapper,
    private val jwtHandler: JwtHandler
) {

    fun loginUser(userLoginPayload: UserLoginPayload): UserSecurityDTO {
        val userSecurityDTO = securityMapper.getUserSecurityDetails(userLoginPayload.email)

        val correctPassword = comparePasswordHash(userLoginPayload.password, userSecurityDTO.passwordHash)


        userSecurityDTO.token = jwtHandler.generateToken(userSecurityDTO)

        return userSecurityDTO
    }

    private fun comparePasswordHash(password: String, passwordHash: String): Boolean {
        return false
    }

    private fun getPasswordHash(password: String): String {
        return ""
    }
}