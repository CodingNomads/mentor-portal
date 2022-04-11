package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.data.ConfigOptionRow
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface UserConfigOptionMapper {
    @Select(SELECT_OPTION_BY_NAME)
    fun selectOptionByName(name: String): ConfigOptionRow

    companion object {
        const val SELECT_OPTION_BY_NAME =
            """
                SELECT
                    id,
                    option_name,
                    description
                FROM user_config_option
                WHERE option_name = #{value}
            """
    }
}