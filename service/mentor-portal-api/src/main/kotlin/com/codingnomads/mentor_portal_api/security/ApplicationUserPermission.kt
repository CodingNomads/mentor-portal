package com.codingnomads.mentor_portal_api.security

enum class ApplicationUserPermission(val permission: String) {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    MENTOR_READ("mentor:read"),
    MENTOR_WRITE("mentor:write"),
    ADMIN_READ("admin:read")
}
