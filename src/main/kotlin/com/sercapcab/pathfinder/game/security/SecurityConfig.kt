package com.sercapcab.pathfinder.game.security

import com.sercapcab.pathfinder.game.service.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .csrf { it.disable() }
            .authorizeHttpRequests {
                http ->

            }
    }

    @Bean
    fun roleHierachy(): RoleHierarchy {
        val roleHierarchy: RoleHierarchyImpl = RoleHierarchyImpl()
        val hierarchy: String = "ROLE_ADMIN > ROLE_DEVELOPER \n ROLE_DEVELOPER > ROLE_USER"
        roleHierarchy.setHierarchy(hierarchy)

        return roleHierarchy
    }

    @Bean
    fun customWebSecurityExpressionHandler(): DefaultWebSecurityExpressionHandler {
        val expressionHandler: DefaultWebSecurityExpressionHandler = DefaultWebSecurityExpressionHandler()

        expressionHandler.setRoleHierarchy(roleHierachy())

        return expressionHandler
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun authenticationProvider(userDetailsServiceImpl: UserDetailsServiceImpl): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(passwordEncoder())
        provider.setUserDetailsService(userDetailsServiceImpl)

        return provider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
        //return BCryptPasswordEncoder()
    }
}

//    @Bean
//    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
//        return httpSecurity
//            .csrf {csrf -> csrf.disable()}
//            .httpBasic(Customizer.withDefaults())
//            .sessionManagement {session ->
//                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            }
//            .authorizeHttpRequests {http ->
//                http.requestMatchers(HttpMethod.GET, "/api/v1/player").hasRole("USER")
//
//                http.anyRequest().permitAll()
//                http.requestMatchers(HttpMethod.GET, "/api/v1/player/**")
//                    .hasAuthority("READ_PLAYER_DATA")
//                http.requestMatchers(HttpMethod.GET, "/api/v1/unit-stat/**", "/api/v1/creature/**", "/api/v1/spell/**")
//                    .hasAuthority("READ_GAME_DATA")
//
//                http.requestMatchers(HttpMethod.POST, "/api/v1/player/**", "/api/v1/character/**")
//                    .hasAuthority("WRITE_PLAYER_DATA")
//                http.requestMatchers(HttpMethod.POST, "/api/v1/unit-stat/**", "/api/v1/creature/**", "/api/v1/spell/**")
//                    .hasAuthority("WRITE_GAME_DATA")
//
//                http.requestMatchers(HttpMethod.PUT, "/api/v1/player/**", "/api/v1/character/**")
//                    .hasAuthority("UPDATE_PLAYER_DATA")
//                http.requestMatchers(HttpMethod.PUT, "/api/v1/unit-stat/**", "/api/v1/creature/**", "/api/v1/spell/**")
//                    .hasAuthority("UPDATE_GAME_DATA")
//
//                http.requestMatchers(HttpMethod.DELETE, "/api/v1/player", "/api/v1/character")
//                    .hasAuthority("DELETE_PLAYER_DATA")
//                http.requestMatchers(HttpMethod.DELETE, "/api/v1/unit-stat/**", "/api/v1/creature/**", "/api/v1/spell/**")
//                    .hasAuthority("DELETE_GAME_DATA")
//
//                http.requestMatchers(HttpMethod.GET, "/api/v1/user")
//                    .permitAll()
//
//                http.anyRequest().permitAll()
//