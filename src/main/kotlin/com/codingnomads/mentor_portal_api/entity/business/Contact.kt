package com.codingnomads.mentor_portal_api.entity.business

import com.codingnomads.mentor_portal_api.entity.data.ContactRow

data class Contact(
    val id:Int? = null,
    val userId: Int,
    val email: String,
    val telephone: String,
    val forumUsername: String,
    val slackUsername: String,
){
    companion object {
        fun fromEntity(contactRow: ContactRow) = Contact (
            id = contactRow.id,
            userId = contactRow.userId,
            email = contactRow.email,
            telephone = contactRow.telephone,
            forumUsername = contactRow.forumUsername,
            slackUsername = contactRow.slackUsername
                )
    }
}
