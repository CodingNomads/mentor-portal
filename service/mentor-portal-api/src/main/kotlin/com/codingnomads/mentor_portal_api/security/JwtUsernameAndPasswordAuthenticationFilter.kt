package com.codingnomads.mentor_portal_api.security

import com.codingnomads.mentor_portal_api.entity.business.UserLoginPayload
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.time.LocalDate
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtUsernameAndPasswordAuthenticationFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {

        val objectMapper = jacksonObjectMapper()

        val userLoginPayload: UserLoginPayload

        if (request != null) {
            userLoginPayload = objectMapper.readValue(request.inputStream)
            val authentication = UsernamePasswordAuthenticationToken(
                // principle
                userLoginPayload.username,
                //password
                userLoginPayload.password
            )

            return authenticationManager.authenticate(authentication)
        } else {
            throw UnknownError("Failed to authenticate")
        }

    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        if (authResult != null) {
            val compactJwt = Jwts.builder()
                .setSubject(authResult.name)
                .claim("authorities", authResult.authorities)
                .setIssuedAt(Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(SignatureAlgorithm.HS256, "APP_SECRET")
                .compact()

            response?.addHeader("Authorization", "Bearer $compactJwt")
        }
    }
}