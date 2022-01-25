package com.codingnomads.mentor_portal_api.mappers

import com.codingnomads.mentor_portal_api.entities.data.ContactRow
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ContactMapper {
    /**
     * Insert contact data
     */
    @Insert(INSERT_CONTACT_STATEMENT + INSERT_CONTACT_VALUES)
    fun insertContact(contactRow: ContactRow): Int

    companion object{
        const val INSERT_CONTACT_STATEMENT =
            """
                INSERT INTO contact
                (
                user_id,
                email,
                telephone,
                forum_username,
                slack_username
                )
            """
        const val INSERT_CONTACT_VALUES = "VALUES (#{userId}, #{email}, #{telephone}, #{forumUsername}, #{slackUsername})"
    }
}