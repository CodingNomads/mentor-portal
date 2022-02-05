package com.codingnomads.mentor_portal_api.mapper

import com.codingnomads.mentor_portal_api.entity.data.ConfigValueRow
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserConfigValueMapper {
    @Insert(INSERT_CONFIG_VALUE_STATEMENT + INSERT_CONFIG_VALUE_VALUES)
    fun insertConfigValue(configValueRow: ConfigValueRow)

    companion object {
        const val INSERT_CONFIG_VALUE_STATEMENT =
            """
                INSERT INTO user_config_value
                (
                    option_id,
                    user_id,
                    value
                )
            """

        const val INSERT_CONFIG_VALUE_VALUES =
            """
                VALUES
                (
                    #{optionId},
                    #{userId},
                    #{value}
                )
            """
    }
}