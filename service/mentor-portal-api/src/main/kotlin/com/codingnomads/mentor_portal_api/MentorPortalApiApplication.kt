package com.codingnomads.mentor_portal_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.util.*


@SpringBootApplication
class MentorPortalApiApplication

fun main(args: Array<String>) {
    runApplication<MentorPortalApiApplication>(*args)
}

// Global cors configuration
// Cors Configuration was moved to Security
//@Configuration
//class MyConfiguration {
//    @Bean
//    fun corsConfigurer(): WebMvcConfigurer {
//        return object: WebMvcConfigurer {
//            override fun addCorsMappings(registry: CorsRegistry) {
//                registry.addMapping("/**")
//                    .allowedOrigins(System.getenv("CLIENT_BASE_URL"))
//                    .allowedMethods("*")
//            }
//        }
//    }
//}
