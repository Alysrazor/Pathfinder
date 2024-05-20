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
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { http ->
                // Ruta Auth ( Ruta de prueba )
                http.requestMatchers("/api/v1/auth/ping").permitAll()
                http.requestMatchers("/api/v1/auth/hello").permitAll()
                http.requestMatchers("/api/v1/auth/hello-secured").hasAnyAuthority("READ_GAME_DATA", "READ_PLAYER_DATA")

                // Ruta de las estadísticas de personajes y criaturas
                http.requestMatchers(HttpMethod.GET, "/api/v1/unit-stat/**")
                    .hasAnyAuthority("READ_GAME_DATA", "READ_PLAYER_DATA")

                // Ruta Datos de juego
                http.requestMatchers(HttpMethod.GET,
                    "/api/v1/creature/**",
                    "/api/v1/spell/**"
                ).hasAuthority("READ_GAME_DATA")

                http.requestMatchers(HttpMethod.POST,
                    "/api/v1/unit-stat/**",
                    "/api/v1/creature/**",
                    "/api/v1/spell/**").hasAuthority("WRITE_GAME_DATA")

                http.requestMatchers(HttpMethod.PUT,
                    "/api/v1/unit-stat/**",
                    "/api/v1/creature/**",
                    "/api/v1/spell/**").hasAuthority("UPDATE_GAME_DATA")

                http.requestMatchers(HttpMethod.DELETE,
                    "/api/v1/unit-stat/**",
                    "/api/v1/creature/**",
                    "/api/v1/spell/**").hasAuthority("DELETE_GAME_DATA")

                // Ruta Datos de Jugador
                http.requestMatchers(HttpMethod.GET,
                    "/api/v1/character/**",
                    "/api/v1/player/**")
                    .hasAuthority("READ_PLAYER_DATA")

                http.requestMatchers(HttpMethod.POST,
                    "/api/v1/character/**",
                    "/api/v1/player/**")
                    .hasAuthority("WRITE_PLAYER_DATA")

                http.requestMatchers(HttpMethod.PUT,
                    "/api/v1/character/**",
                    "/api/v1/player/**")
                    .hasAuthority("UPDATE_PLAYER_DATA")

                http.requestMatchers(HttpMethod.DELETE,
                    "/api/v1/character/**",
                    "/api/v1/player/**")
                    .hasAuthority("DELETE_PLAYER_DATA")

                // Ruta de Roles
                http.requestMatchers(HttpMethod.GET,
                    "/api/v1/role/**",
                    "/api/v1/role-permission/**")
                    .hasRole("ROLE_ADMINISTRATOR")

                http.requestMatchers(HttpMethod.POST,
                    "/api/v1/role/**",
                    "/api/v1/role-permission/**")
                    .hasRole("ROLE_ADMINISTRATOR")

                http.requestMatchers(HttpMethod.PUT,
                    "/api/v1/role/**",
                    "/api/v1/role-permission/**")
                    .hasRole("ROLE_ADMINISTRATOR")

                http.requestMatchers(HttpMethod.DELETE,
                    "/api/v1/role/**",
                    "/api/v1/role-permission/**")
                    .hasRole("ROLE_ADMINISTRATOR")
            }
            .httpBasic(Customizer.withDefaults())

        return httpSecurity.build()
    }


    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(passwordEncoder())
        provider.setUserDetailsService(userDetailsService())

        return provider
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val userDetailsList: MutableList<UserDetails> = mutableListOf()

        userDetailsList.addAll(
            listOf(
                User
                    .withUsername("Alysrazor")
                    .password(passwordEncoder().encode("alysrazor"))
                    .roles("ADMINISTRATOR", "DEVELOPER", "USER", "GUEST")
                    .authorities(
                        "READ_GAME_DATA",
                        "READ_PLAYER_DATA",
                        "WRITE_GAME_DATA",
                        "WRITE_PLAYER_DATA",
                        "UPDATE_GAME_DATA",
                        "UPDATE_PLAYER_DATA",
                        "DELETE_GAME_DATA",
                        "DELETE_PLAYER_DATA"
                    )
                    .build(),
                User
                    .withUsername("Android")
                    .password(passwordEncoder().encode("android"))
                    .roles("USER", "GUEST")
                    .authorities(
                        "READ_PLAYER_DATA",
                        "WRITE_PLAYER_DATA",
                        "UPDATE_PLAYER_DATA",
                        "DELETE_PLAYER_DATA"
                    )
                    .build()
            )
        )

        return InMemoryUserDetailsManager(userDetailsList)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}

