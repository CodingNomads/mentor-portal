package com.codingnomads.mentor_portal_api.security

import com.google.common.collect.Sets

import com.codingnomads.mentor_portal_api.security.ApplicationUserPermission.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class ApplicationUserRole() {
    MENTOR () {
        override fun getGrantedAuthorities(): Set<SimpleGrantedAuthority> {
            return Sets.newHashSet(STUDENT_READ, STUDENT_WRITE).map {value -> SimpleGrantedAuthority(value.permission)}.toSet()
        }

    },

    ADMIN () {
        override fun getGrantedAuthorities(): Set<GrantedAuthority> {
            return Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, MENTOR_READ, MENTOR_WRITE, ADMIN_READ).map {value -> SimpleGrantedAuthority(value.permission)}.toSet()
        }

    };

    abstract fun getGrantedAuthorities(): Set<GrantedAuthority>

}