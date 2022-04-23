package com.codingnomads.mentor_portal_api.security

import com.codingnomads.mentor_portal_api.entity.business.UserSecurityDTO
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtHandler() {
    fun generateToken(userSecurityDTO: UserSecurityDTO): String {
        val issuer = userSecurityDTO.userId.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000))
            .signWith(SignatureAlgorithm.HS512, "secret")
            .claim("role", "Admin")
            .compact()

        return jwt
    }

    fun verify(jwt: String): Authentication {

        return false
    }
}