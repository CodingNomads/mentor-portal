package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.ApplicationUser
import com.codingnomads.mentor_portal_api.mapper.SecurityMapper
import com.codingnomads.mentor_portal_api.security.ApplicationUserRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class ApplicationUserDetailsService(@Autowired private val securityMapper: SecurityMapper) :UserDetailsService {
    override fun loadUserByUsername(username: String?): ApplicationUser {
        if (username == null) {
            throw UsernameNotFoundException("User Not Found")
        }

        val userSecurityDTO = securityMapper.getUserSecurityDetails(email = username)

        var grantedAuthorities = ApplicationUserRole.MENTOR.getGrantedAuthorities();
        if (userSecurityDTO.isAdmin) {
            grantedAuthorities = ApplicationUserRole.ADMIN.getGrantedAuthorities()
        }

        return ApplicationUser(
            grantedAuthorities = grantedAuthorities.toMutableList(),
            password = userSecurityDTO.passwordHash,
            username = userSecurityDTO.email,
            isAccountNonExpired = true,
            isAccountNonLocked = true,
            isCredentialsNonExpired = true,
            isEnabled = true)
    }

}