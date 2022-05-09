package com.codingnomads.mentor_portal_api.entity.data

import com.codingnomads.mentor_portal_api.entity.business.Contact

data class ContactRow(
    val id:Int? = null,
    val userId: Int,
    val telephone: String,
    val location: String,
    val forumUsername: String,
    val slackUsername: String,
)