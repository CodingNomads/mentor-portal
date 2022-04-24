package com.codingnomads.mentor_portal_api.security

import com.codingnomads.mentor_portal_api.handler.UserSecurityHandler
import com.codingnomads.mentor_portal_api.mapper.SecurityMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
class SecurityConfiguration(
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired private val userSecurityHandler: UserSecurityHandler) : WebSecurityConfigurerAdapter(

) {
    override fun configure(http: HttpSecurity?) {
        http?.let {
            it
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticationFilter::class.java)
                .authorizeRequests()
                .antMatchers("/status")
                .permitAll()
                .anyRequest()
                .authenticated()
        }
    }


    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(daoAuthenticationProvider())
    }

    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder)
        daoAuthenticationProvider.setUserDetailsService(userSecurityHandler)

        return daoAuthenticationProvider
    }
}