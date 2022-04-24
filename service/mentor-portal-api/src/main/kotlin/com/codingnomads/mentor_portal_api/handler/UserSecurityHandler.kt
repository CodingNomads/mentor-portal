package com.codingnomads.mentor_portal_api.handler

import com.codingnomads.mentor_portal_api.entity.ApplicationUser
import com.codingnomads.mentor_portal_api.mapper.SecurityMapper
import com.codingnomads.mentor_portal_api.security.ApplicationUserRole
import com.google.common.collect.Sets
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserSecurityHandler(@Autowired private val securityMapper: SecurityMapper) :UserDetailsService {
    override fun loadUserByUsername(username: String?): ApplicationUser {
        val userSecurityDTO = username?.let { securityMapper.getUserSecurityDetails(it) }
            ?: throw UsernameNotFoundException("User Not Found")

        var grantedAuthorities = ApplicationUserRole.MENTOR.getGrantedAuthorities();
        if (userSecurityDTO.isAdmin == true) {
            grantedAuthorities = ApplicationUserRole.ADMIN.getGrantedAuthorities()
        }

        return ApplicationUser(grantedAuthorities = grantedAuthorities.toMutableList(), password = userSecurityDTO.passwordHash!!, username = userSecurityDTO.username!!, isAccountNonExpired = true, isAccountNonLocked = true, isCredentialsNonExpired = true, isEnabled = true)
    }

}