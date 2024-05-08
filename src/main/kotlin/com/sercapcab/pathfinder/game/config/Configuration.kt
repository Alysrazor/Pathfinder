package com.sercapcab.pathfinder.game.config

import com.sercapcab.pathfinder.game.entity.unit.Unit
import com.sercapcab.pathfinder.game.entity.unit.UnitService
import com.sercapcab.pathfinder.game.entity.unit.unitstat.UnitStat
import com.sercapcab.pathfinder.game.entity.unit.unitstat.UnitStatService
import com.sercapcab.pathfinder.game.enumeration.SpellSchool
import com.sercapcab.pathfinder.game.enumeration.Stat
import com.sercapcab.pathfinder.game.enumeration.UnitClass
import com.sercapcab.pathfinder.game.spell.Spell
import com.sercapcab.pathfinder.game.spell.SpellService
import org.jetbrains.annotations.NotNull
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

/*@Configuration
class Configurator {
    @Bean
    @NotNull
    fun commandLineRunner(
        @NotNull unitService: UnitService,
        @NotNull unitStatService: UnitStatService,
        @NotNull spellService: SpellService
    ): CommandLineRunner {
        return CommandLineRunner { args ->
            println("Executing CommandLineRunner...")
            println("Adding UnitStats to DB...")
            val unitStat =
                UnitStat(
                    uuid = UUID.randomUUID(),
                    strength = 10,
                    dexterity = 10,
                    constitution = 10,
                    intelligence = 10,
                    wisdom = 10,
                    charisma = 10,
                    units = setOf(),
                    comment = "Estadísticas por defecto"
                )
            println("Adding Spells to DB...")
            val spell =
                Spell(
                    spellUuid = UUID.randomUUID(),
                    spellName = "Bola de Fuego",
                    spellSchool = SpellSchool.SCHOOL_FIRE,
                    baseDamage = 20,
                    statModifier = Stat.STAT_INTELLIGENCE,
                    statMultiplier = 1.2,
                    units = mutableSetOf()
                )
            println("Adding Units to DB...")
            val unit =
                Unit(
                    uuid = UUID.randomUUID(),
                    name = "Manolo GPT",
                    level = 1u,
                    unitArmor = 10,
                    unitMagicResistance = 10,
                    unitClass = UnitClass.CLASS_CLERIC,
                    unitStat = unitStat,
                    unitSpells = mutableSetOf(spell),
                    unitModel = 0,
                    comment = "Dummy"
                )

            println(unit.uuid)
        }
    }
}*/
