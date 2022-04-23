package com.codingnomads.mentor_portal_api.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfiguration : WebSecurityConfigurerAdapter(

) {
    @Autowired
    lateinit var filter: JwtAuthorizationFilter
    lateinit var authEntryPoint: AuthEntryPoint

    override fun configure(http: HttpSecurity?) {
        http?.let {
            it.cors().and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/**").permitAll()
                .antMatchers("/status").permitAll()
                .anyRequest().authenticated();

            http.addFilterBefore(filter, BasicAuthenticationFilter::class.java)
        }
    }
}