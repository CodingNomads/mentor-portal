package com.codingnomads.mentor_portal_api.security

import com.google.common.collect.Sets
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenVerifier(): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(Headers.AUTHORIZATION)

        if (authHeader.isNullOrEmpty() || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        try {
            val token = authHeader.replace("Bearer ", "")
            val claims = Jwts.parser().setSigningKey("APP_SECRET").parseClaimsJws(token)

            val body = claims.body
            val username = body.subject
            val authorities: List<Map<String, String>> = body["authorities"] as List<Map<String, String>>

            val grantedAuthorities = Sets.newHashSet<GrantedAuthority>()
            for (authority in authorities) {
                grantedAuthorities.add(SimpleGrantedAuthority(authority["authority"]))
            }

            val authentication = UsernamePasswordAuthenticationToken(
                username,
                null,
                grantedAuthorities
            )

            SecurityContextHolder.getContext().authentication = authentication

        } catch (e: JwtException) {
            throw IllegalStateException("Bearer Token is invalid.")
        }

        filterChain.doFilter(request, response)
    }
}