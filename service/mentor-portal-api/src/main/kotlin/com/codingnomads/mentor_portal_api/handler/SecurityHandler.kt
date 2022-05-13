package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.ResetPasswordPayload
import com.codingnomads.mentor_portal_api.mapper.SecurityMapper
import com.codingnomads.mentor_portal_api.security.JwtTokenVerifier
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class SecurityHandler(@Autowired private val securityMapper: SecurityMapper,
                      @Autowired private val passwordEncoder: PasswordEncoder) {
    fun resetPasswordByEmail(jwtToken: String, resetPasswordPayload: ResetPasswordPayload) {
        val AUTH_HEADER_BEARER_PREFIX = "Bearer "
        val EMPTY_STRING = ""

        try {
            val token = jwtToken.replace(AUTH_HEADER_BEARER_PREFIX, EMPTY_STRING)
            val claims = Jwts.parser().setSigningKey(System.getenv(JwtTokenVerifier.APP_SECRET_ENV_NAME)).parseClaimsJws(token)
            val body = claims.body
            val email = body.subject

            if (resetPasswordPayload.email != email) {
                throw Exception("Invalid Email")
            }
        } catch (e: JwtException) {
            println(e)
            throw IllegalStateException(e.message)
        }


        val user = securityMapper.getUserSecurityDetails(resetPasswordPayload.email)

        val encodedPassword = passwordEncoder.encode(resetPasswordPayload.newPassword)

        securityMapper.updatePassword(encodedPassword, user.userId)
    }
}