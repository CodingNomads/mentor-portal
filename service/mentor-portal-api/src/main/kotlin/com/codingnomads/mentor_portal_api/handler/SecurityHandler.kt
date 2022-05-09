package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.business.ResetPasswordPayload
import com.codingnomads.mentor_portal_api.mapper.SecurityMapper
import com.codingnomads.mentor_portal_api.security.JwtTokenVerifier
import com.google.common.collect.Sets
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException.BadRequest

@Component
class SecurityHandler(@Autowired private val securityMapper: SecurityMapper,
                      @Autowired private val passwordEncoder: PasswordEncoder) {
    fun resetPasswordByEmail(resetPasswordPayload: ResetPasswordPayload, jwtToken: String) {
        try {
            val claims = Jwts.parser().setSigningKey(System.getenv(JwtTokenVerifier.APP_SECRET_ENV_NAME)).parseClaimsJws(jwtToken)
            val body = claims.body
            val email = body.subject

            if (resetPasswordPayload.email != email) {
                throw Exception("Invalid Email")
            }
        } catch (e: JwtException) {
            throw IllegalStateException(e.message)
        }


        val user = securityMapper.getUserSecurityDetails(resetPasswordPayload.email)

        val encodedPassword = passwordEncoder.encode(resetPasswordPayload.newPassword)

        securityMapper.updatePassword(encodedPassword, user.id)
    }
}