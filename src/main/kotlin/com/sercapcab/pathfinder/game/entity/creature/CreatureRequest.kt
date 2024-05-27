package com.sercapcab.pathfinder.game.entity.creature

import com.sercapcab.pathfinder.game.entity.unitstat.UnitStat
import com.sercapcab.pathfinder.game.enumeration.UnitClass
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import com.sercapcab.pathfinder.game.entity.spell.Spell
import java.util.*

data class CreatureRequest(
    var name: String,
    var level: UInt,
    var unitArmor: Int,
    var unitMagicResistance: Int,
    var unitClass: UnitClass,
    var creatureStats: UnitStat,
    val creatureSpells: MutableSet<Spell>,
    var unitModel: Int,
) {
    fun toCreature(): Creature {
        return Creature(
            uuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Creature".toByteArray())),
            name = name,
            level = level.coerceAtMost(10u),
            unitArmor = unitArmor,
            unitMagicResistance = unitMagicResistance,
            unitClass = unitClass,
            creatureStats = creatureStats,
            creatureSpells = creatureSpells,
            unitModel = unitModel,
        )
    }
}
