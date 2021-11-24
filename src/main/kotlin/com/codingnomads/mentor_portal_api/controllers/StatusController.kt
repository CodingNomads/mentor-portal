package com.codingnomads.mentor_portal_api.controllers

import com.codingnomads.mentor_portal_api.entities.business.Status
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatusController {

    @CrossOrigin(origins = ["http://localhost:5000"])
    @GetMapping("status")
    fun getStatus(): ResponseEntity<Status> {
        val status = Status("Ok")

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(status)
    }
}