package com.codingnomads.mentor_portal_api.entity.data

import com.codingnomads.mentor_portal_api.entity.business.Contact

data class ContactRow(
    val id:Int? = null,
    val userId: Int,
    val email: String,
    val telephone: String,
    val location: String,
    val forumUsername: String,
    val slackUsername: String,
){
    companion object {
        fun fromEntity(contact: Contact) = ContactRow (
            id = contact.id,
            userId = contact.userId,
            email = contact.email,
            telephone = contact.telephone,
            location = contact.location,
            forumUsername = contact.forumUsername,
            slackUsername = contact.slackUsername
        )
    }
}