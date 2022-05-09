package com.codingnomads.mentor_portal_api.security

import com.google.common.collect.Sets
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenVerifier(): OncePerRequestFilter() {
    companion object {
        const val AUTH_HEADER_BEARER_PREFIX = "Bearer "
        const val APP_SECRET_ENV_NAME = "APP_SECRET"
        const val EMPTY_STRING = ""
        const val MAP_KEY_AUTHORITIES = "authorities"
        const val MAP_KEY_AUTHORITY = "authority"

        const val ERROR_TOKEN_IS_INVALID = "Bearer Token is invalid."
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(Headers.AUTHORIZATION)

        if (authHeader.isNullOrEmpty() || !authHeader.startsWith(AUTH_HEADER_BEARER_PREFIX)) {
            filterChain.doFilter(request, response)
            return
        }

        try {
            val token = authHeader.replace(AUTH_HEADER_BEARER_PREFIX, EMPTY_STRING)
            val claims = Jwts.parser().setSigningKey(System.getenv(APP_SECRET_ENV_NAME)).parseClaimsJws(token)
            val body = claims.body
            val username = body.subject
            println(username)

            val authorities: List<Map<String, String>> = body[MAP_KEY_AUTHORITIES] as List<Map<String, String>>

            val grantedAuthorities = Sets.newHashSet<GrantedAuthority>()
            for (authority in authorities) {
                grantedAuthorities.add(SimpleGrantedAuthority(authority[MAP_KEY_AUTHORITY]))
            }

            val authentication = UsernamePasswordAuthenticationToken(
                username,
                null,
                grantedAuthorities
            )

            SecurityContextHolder.getContext().authentication = authentication

        } catch (e: JwtException) {
            throw IllegalStateException(e.message)
        }

        filterChain.doFilter(request, response)
    }
}