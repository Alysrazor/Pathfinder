package com.sercapcab.pathfinder.game.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import kotlin.jvm.Throws

@Configuration
@EnableMethodSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .csrf { csrf -> csrf.disable() }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { http ->
                http
                    // Request de Auth
                    .requestMatchers(HttpMethod.POST, "/api/v1/auth/signin", "/api/v1/auth/signup").permitAll()

                    // Request de Game Data
                    .requestMatchers(HttpMethod.GET, "/api/v1/creature/**", "/api/v1/spell/**", "/api/v1/unit-stat").hasAnyRole("DEVELOPER", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/v1/creature/**", "/api/v1/spell/**", "/api/v1/unit-stat").hasAnyRole("DEVELOPER", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/creature/**", "/api/v1/spell/**", "/api/v1/unit-stat").hasAnyRole("DEVELOPER", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/creature/**", "/api/v1/spell/**", "/api/v1/unit-stat").hasAnyRole("DEVELOPER", "ADMIN")

                    // Request de Player Data
                    .requestMatchers(HttpMethod.GET,
                        "/api/v1/account/username/**",
                        "/api/v1/account/email/**",
                        "/api/v1/character/**").hasAnyRole("USER", "DEVELOPER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/v1/account/").hasAnyRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/v1/account/", "/api/v1/character/**").hasAnyRole("DEVELOPER", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/account/", "/api/v1/character/**").hasAnyRole("DEVELOPER", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/account/", "/api/v1/character/**").hasAnyRole("DEVELOPER", "ADMIN")
                    .anyRequest().authenticated()
            }
            .httpBasic(Customizer.withDefaults())

        return httpSecurity.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager {
        return configuration.authenticationManager
    }

    @Bean
    fun authenticationProvider(userDetailsService: MyUserDetailsService): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(passwordEncoder())
        provider.setUserDetailsService(userDetailsService)

        return provider
    }
}
