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
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtUsernameAndPasswordAuthenticationFilter(
    authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter(authenticationManager) {
    companion object {
        const val APP_SECRET_ENV_NAME = "APP_SECRET"
        const val TOKEN_EXPIRATION_ENV_NAME = "TOKEN_EXPIRATION_IN_MINUTES"
        const val EMPTY_STRING = ""
        const val MAP_KEY_AUTHORITIES = "authorities"
    }

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
        val expiration = Date.from(ZonedDateTime.now().plusMinutes(System.getenv(TOKEN_EXPIRATION_ENV_NAME).toLong()).toInstant())

        if (authResult != null) {
            val compactJwt = Jwts.builder()
                .setSubject(authResult.name)
                .claim(MAP_KEY_AUTHORITIES, authResult.authorities)
                .setIssuedAt(Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, System.getenv(APP_SECRET_ENV_NAME))
                .compact()

            response?.addHeader(Headers.AUTHORIZATION, "Bearer $compactJwt")
        }
    }
}