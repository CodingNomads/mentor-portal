package com.codingnomads.mentor_portal_api.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class User(private val user: String, private val scopes: List<String>) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return scopes
            .map { scope -> SimpleGrantedAuthority(scope) }
            .toMutableList()
    }

    override fun isEnabled() = true

    override fun getUsername() = user

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = ""

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}