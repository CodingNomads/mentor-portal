package com.codingnomads.mentor_portal_api.entity.business

data class CourseTrackData(
    val optionId: Int,
    val userId: Int,
    val courseTrack: String? = ""
)