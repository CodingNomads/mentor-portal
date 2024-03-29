package com.codingnomads.mentor_portal_api.entity.business

data class UserUpdatePayload(
    val userId: Int,
    val bio: String? = null,
    val flag: Boolean? = null,
    val courseTrack:String? = null,
    val mentorshipOption: String? = null,
    val statusDescription: String? = null,
    val programStart: String? = null,
    val programEnd: String? = null,
    val review: Boolean? = null,
    val linkedinAlumni: Boolean? = null
)