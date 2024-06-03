package com.sercapcab.pathfinder.game.config

import com.sercapcab.pathfinder.game.entity.account.Account
import com.sercapcab.pathfinder.game.entity.account.AccountRequest
import com.sercapcab.pathfinder.game.entity.account.AccountService
import com.sercapcab.pathfinder.game.entity.role.Role
import com.sercapcab.pathfinder.game.entity.role.RoleEnum
import com.sercapcab.pathfinder.game.entity.role.RoleService
import com.sercapcab.pathfinder.game.entity.spell.SpellRequest
import com.sercapcab.pathfinder.game.entity.spell.SpellService
import com.sercapcab.pathfinder.game.entity.unitstat.UnitStatRequest
import com.sercapcab.pathfinder.game.entity.unitstat.UnitStatService
import com.sercapcab.pathfinder.game.enumeration.SpellSchool
import com.sercapcab.pathfinder.game.enumeration.Stat
import com.sercapcab.pathfinder.game.security.MyUserDetailsService
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import org.jetbrains.annotations.NotNull
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

//import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class Configurator {
    @Bean
    @NotNull
    fun commandLineRunner(
        accountService: AccountService,
        roleService: RoleService,
        unitStatService: UnitStatService,
        spellService: SpellService,
        userDetailsService: MyUserDetailsService
    ): CommandLineRunner {
        return CommandLineRunner { _ ->
            val adminRole = Role(
                roleId = 1,
                roleName = RoleEnum.ADMIN
            )
            val developerRole = Role(
                roleId = 2,
                roleName = RoleEnum.DEVELOPER
            )
            val userRole = Role(
                roleId = 3,
                roleName = RoleEnum.USER
            )

            //roleService.save(adminRole)
            //roleService.save(developerRole)
            //roleService.save(userRole)

            val alysrazorAccount = Account(
                accountUuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Account".toByteArray())),
                username = "Alysrazor",
                email = "alysrazor@rpgduels.es",
                password = BCryptPasswordEncoder().encode("alysrazor"),
                roles = mutableSetOf(adminRole, developerRole, userRole)
            )

            val rpgduelsAccount = Account(
                accountUuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Account".toByteArray())),
                username = "RPGDuels",
                email = "rpgduels@rpgduels.es",
                password = BCryptPasswordEncoder().encode("rpgduels"),
                roles = mutableSetOf(adminRole, developerRole, userRole)
            )

            val androidAccount = Account(
                accountUuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Account".toByteArray())),
                username = "Android",
                email = "android@android.15",
                password = BCryptPasswordEncoder().encode("android"),
                roles = mutableSetOf(developerRole, userRole)
            )

            //accountService.save(alysrazorAccount)
            //accountService.save(rpgduelsAccount)
            //accountService.save(androidAccount)

            // Stats and Spells
            val mageStats = UnitStatRequest(
                strength = 8,
                dexterity = 13,
                constitution = 15,
                intelligence = 17,
                wisdom = 10,
                charisma = 12,
                comment = "Player Mage Stats"
            )

            val paladinStats = UnitStatRequest(
                strength = 14,
                dexterity = 13,
                constitution = 16,
                intelligence = 8,
                wisdom = 12,
                charisma = 14,
                comment = "Player Paladin Stats"
            )

            val rogueStats = UnitStatRequest(
                strength = 12,
                dexterity = 17,
                constitution = 14,
                intelligence = 10,
                wisdom = 8,
                charisma = 13,
                comment = "Player Rogue Stats"
            )

            val fighterStats = UnitStatRequest(
                strength = 16,
                dexterity = 14,
                constitution = 15,
                intelligence = 8,
                wisdom = 11,
                charisma = 12,
                comment = "Player Fighter Stats"
            )

            val generalSpell = SpellRequest(
                spellName = "Atacar",
                spellDescription = "Ataca al objetivo con tu arma.",
                spellSchool = SpellSchool.SCHOOL_SLASHING,
                baseDamage = 8,
                baseManaCost = 0,
                statModifier = Stat.STAT_STRENGTH,
                statMultiplier = 1.0
            )

            val mageSpells = setOf(
                SpellRequest(
                    spellName = "Bola de Fuego",
                    spellDescription = "Lanza una bola de fuego al objetivo.",
                    spellSchool = SpellSchool.SCHOOL_FIRE,
                    baseDamage = 20,
                    baseManaCost = 30,
                    statModifier = Stat.STAT_INTELLIGENCE,
                    statMultiplier = 1.15
                ),
                SpellRequest(
                    spellName = "Descarga de Escarcha",
                    spellDescription = "Lanza una lanza de hielo al objetivo.",
                    spellSchool = SpellSchool.SCHOOL_COLD,
                    baseDamage = 12,
                    baseManaCost = 14,
                    statModifier = Stat.STAT_INTELLIGENCE,
                    statMultiplier = 1.08
                ),
            )

            val paladinSpells = setOf(
                SpellRequest(
                    spellName = "Espada Sagrada",
                    spellDescription = "Ataca al objetivo con su arma imbuída de energía sagrada",
                    spellSchool = SpellSchool.SCHOOL_RADIANT,
                    baseDamage = 15,
                    baseManaCost = 15,
                    statModifier = Stat.STAT_STRENGTH,
                    statMultiplier = 1.10
                ),
                SpellRequest(
                    spellName = "Sentencia",
                    spellDescription = "Lanza un mazo de justicia al objetivo.",
                    spellSchool = SpellSchool.SCHOOL_RADIANT,
                    baseDamage = 10,
                    baseManaCost = 8,
                    statModifier = Stat.STAT_CONSTITUTION,
                    statMultiplier = 1.05
                ),
            )

            val rogueSpells = setOf(
                SpellRequest(
                    spellName = "Puñalada",
                    spellDescription = "Apuñala al objetivo infligiendo daño físico",
                    spellSchool = SpellSchool.SCHOOL_SLASHING,
                    baseDamage = 15,
                    baseManaCost = 40,
                    statModifier = Stat.STAT_DEXTERITY,
                    statMultiplier = 1.30
                ),
                SpellRequest(
                    spellName = "Arrojar daga",
                    spellDescription = "Lanza una daga al objetivo.",
                    spellSchool = SpellSchool.SCHOOL_PIERCING,
                    baseDamage = 5,
                    baseManaCost = 15,
                    statModifier = Stat.STAT_DEXTERITY,
                    statMultiplier = 1.15
                ),
            )

            val fighterSpells = setOf(
                SpellRequest(
                    spellName = "Golpe contundente",
                    spellDescription = "Golpe con su arma de forma contundente al objetivo.",
                    spellSchool = SpellSchool.SCHOOL_BLUDGEONING,
                    baseDamage = 20,
                    baseManaCost = 35,
                    statModifier = Stat.STAT_STRENGTH,
                    statMultiplier = 1.2
                )
            )

            /*unitStatService.save(mageStats.toUnitStat())
            unitStatService.save(paladinStats.toUnitStat())
            unitStatService.save(rogueStats.toUnitStat())
            unitStatService.save(fighterStats.toUnitStat())

            mageSpells.forEach { spell -> spellService.save(spell.toSpell()) }
            paladinSpells.forEach { spell -> spellService.save(spell.toSpell()) }
            rogueSpells.forEach { spell -> spellService.save(spell.toSpell()) }
            fighterSpells.forEach { spell -> spellService.save(spell.toSpell()) }*/
        }
    }
}
