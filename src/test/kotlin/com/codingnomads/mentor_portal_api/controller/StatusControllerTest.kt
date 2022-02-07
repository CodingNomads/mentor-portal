package com.codingnomads.mentor_portal_api.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StatusControllerTest {
    private val statusController = StatusController()

    @Test
    fun `should get status` () {
        val status = statusController.getStatus()
        assertThat(status.body?.Status).isEqualTo("Ok")
    }
}