package com.codingnomads.mentor_portal_api.security

import arrow.core.Either
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.stereotype.Component

/**
 * Base interface for a class that can verify JWT tokens
 */
interface Verifier {
    /**
     * @param jwt a jwt token
     * @return whether the token is valid or not
     */
    fun verify(jwt: String): Either<JWTVerificationException, TokenAuthentication>
}