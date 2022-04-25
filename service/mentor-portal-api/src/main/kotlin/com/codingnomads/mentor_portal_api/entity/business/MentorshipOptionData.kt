package com.codingnomads.mentor_portal_api.entity.business

data class MentorshipOptionData(
    val optionId: Int,
    val userId: Int,
    val mentorshipOption: String? = ""
)