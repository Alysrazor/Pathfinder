package com.sercapcab.pathfinder.game.config

import com.sercapcab.pathfinder.game.security.generateRandomSecret
import org.jetbrains.annotations.NotNull
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configurator {
    @Bean
    @NotNull
    fun commandLineRunner(
    ): CommandLineRunner {
        return CommandLineRunner { _ ->
            println(generateRandomSecret(32))
        }
    }
}
