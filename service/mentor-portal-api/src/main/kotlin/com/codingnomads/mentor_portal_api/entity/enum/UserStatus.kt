package com.codingnomads.mentor_portal_api.entity.enum

enum class UserStatus (val description: String, val code: Int) {
    ACTIVE(description = "Active", code = 100),
    PAUSED(description = "Paused", code = 200),
    CANCELLED(description = "Cancelled", code = 300),
    COMPLETED_PROGRAM(description = "Completed program", code = 400),
    COMPLETED_CURRICULUM(description = "Completed curriculum", code = 500),
    DROPPED_OUT(description = "Dropped out", code = 333),
    MIA(description = "MIA", code = 666)
}