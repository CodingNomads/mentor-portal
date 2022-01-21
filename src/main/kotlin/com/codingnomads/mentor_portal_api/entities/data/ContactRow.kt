package com.codingnomads.mentor_portal_api.entities.data

import com.codingnomads.mentor_portal_api.entities.business.Contact

data class ContactRow(
    val id:Int,
    val userId: Int,
    val email: String,
    val telephone: String,
    val forumUsername: String,
    val slackUsername: String,
){
    companion object {
        fun fromEntity(contact: Contact) = ContactRow (
            id = contact.id,
            userId = contact.userId,
            email = contact.email,
            telephone = contact.telephone,
            forumUsername = contact.forumUsername,
            slackUsername = contact.slackUsername
        )
    }
}