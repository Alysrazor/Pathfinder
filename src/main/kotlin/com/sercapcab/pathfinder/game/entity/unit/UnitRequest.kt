package com.sercapcab.pathfinder.game.entity.unit

import com.sercapcab.pathfinder.game.entity.unit.unitstat.UnitStat
import com.sercapcab.pathfinder.game.enumeration.UnitClass
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import com.sercapcab.pathfinder.game.spell.Spell
import java.util.*

data class UnitRequest(
    var name: String,
    var level: UInt,
    var unitArmor: Int,
    var unitMagicResistance: Int,
    var unitClass: UnitClass,
    var unitStat: UnitStat,
    val unitSpells: MutableSet<Spell> = mutableSetOf(),
    var unitModel: Int,
    var comment: String,
) {
    fun toUnit(): Unit {
        return Unit(
            uuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Unit".toByteArray()), UUID.randomUUID().toString()),
            name = name,
            level = level,
            unitArmor = unitArmor,
            unitMagicResistance = unitMagicResistance,
            unitClass = unitClass,
            unitStat = unitStat,
            unitSpells = unitSpells,
            unitModel = unitModel,
            comment = comment
        )
    }
}
