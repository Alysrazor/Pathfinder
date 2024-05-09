package com.sercapcab.pathfinder.game.config

import com.sercapcab.pathfinder.game.entity.unit.Unit
import com.sercapcab.pathfinder.game.entity.unit.UnitService
import com.sercapcab.pathfinder.game.entity.unit.unitstat.UnitStat
import com.sercapcab.pathfinder.game.entity.unit.unitstat.UnitStatService
import com.sercapcab.pathfinder.game.enumeration.SpellSchool
import com.sercapcab.pathfinder.game.enumeration.Stat
import com.sercapcab.pathfinder.game.enumeration.UnitClass
import com.sercapcab.pathfinder.game.security.generateRandomSecret
import com.sercapcab.pathfinder.game.spell.Spell
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
        @NotNull unitService: UnitService,
        @NotNull unitStatService: UnitStatService,
        @NotNull spellService: SpellService
    ): CommandLineRunner {
        return CommandLineRunner { args ->
            println(generateRandomSecret(32))
        }
    }
}
