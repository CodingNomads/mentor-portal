package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.data.ContactRow
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options

@Mapper
interface ContactMapper {
    /**
     * Insert contact data
     */
    @Insert(INSERT_CONTACT_STATEMENT + INSERT_CONTACT_VALUES)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertContact(contactRow: ContactRow): Int

    companion object{
        const val INSERT_CONTACT_STATEMENT =
            """
                INSERT INTO contact
                (
                user_id,
                telephone,
                location,
                forum_username,
                slack_username
                )
            """
        const val INSERT_CONTACT_VALUES = "VALUES (#{userId}, #{telephone}, #{location}, #{forumUsername}, #{slackUsername})"
    }
}