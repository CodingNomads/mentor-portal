package com.codingnomads.mentor_portal_api.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthorizationFilter(private val jwtHandler: JwtHandler) : OncePerRequestFilter() {
    companion object {
        private fun String.extractToken() = if (startsWith("Bearer "))
            split(" ").last()
        else
            null
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token: String? = request.getHeader(Headers.AUTHORIZATION)?.extractToken()
        if (token != null) {

        }


        request.getHeader(Headers.AUTHORIZATION)?.let { header ->
            header.extractToken()?.let { jwt ->
                jwtHandler.verify(jwt).let { token ->
                    SecurityContextHolder.getContext().authentication = token
                }
            }
        }

        filterChain.doFilter(request, response)
    }
}