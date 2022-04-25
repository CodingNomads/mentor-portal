package com.codingnomads.mentor_portal_api.controller

import com.codingnomads.mentor_portal_api.entity.business.Status
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatusController {

    @GetMapping("status")
    fun getStatus(): ResponseEntity<Status> {
        val status = Status("Ok")

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(status)
    }

    @PreAuthorize("hasAuthority('admin:read')")
    @GetMapping("status-admin")
    fun getStatusAuthorized(): ResponseEntity<Status> {
        val status = Status("Ok")

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(status)
    }
}