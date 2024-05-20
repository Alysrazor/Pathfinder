package com.sercapcab.pathfinder.game.config

import com.sercapcab.pathfinder.game.security.generateRandomSecret
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class Configurator {
    @Bean
    @NotNull
    fun commandLineRunner(
        userDetailsService: UserDetailsService
    ): CommandLineRunner {
        return CommandLineRunner { _ ->
            println(generateRandomSecret(32))
            val details = userDetailsService.loadUserByUsername("Alysrazor")
            println(details.authorities)
        }
    }
}
