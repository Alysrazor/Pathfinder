package com.sercapcab.pathfinder.game.config

import com.sercapcab.pathfinder.game.entity.player.Player
import com.sercapcab.pathfinder.game.entity.unitstat.UnitStatService
import com.sercapcab.pathfinder.game.security.checkPassword
import com.sercapcab.pathfinder.game.security.generateRandomSecret
import com.sercapcab.pathfinder.game.spell.SpellService
import org.jetbrains.annotations.NotNull
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class Configurator {
    @Bean
    @NotNull
    fun commandLineRunner(
    ): CommandLineRunner {
        return CommandLineRunner { args ->
            println(generateRandomSecret(32))
        }
    }
}
