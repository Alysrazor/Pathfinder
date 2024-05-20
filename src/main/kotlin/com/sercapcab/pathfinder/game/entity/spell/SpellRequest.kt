package com.sercapcab.pathfinder.game.entity.spell

import com.sercapcab.pathfinder.game.enumeration.SpellSchool
import com.sercapcab.pathfinder.game.enumeration.Stat
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import java.util.*

data class SpellRequest(
    var spellName: String,
    var spellDescription: String,
    var spellSchool: SpellSchool,
    var baseDamage: Int,
    var baseManaCost: Int,
    var statModifier: Stat,
    var statMultiplier: Double,
    var spellModel: Int
) {
    fun toSpell(): Spell {
        return Spell(
            spellUuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Spell".toByteArray()), UUID.randomUUID().toString()),
            spellName = spellName,
            spellDescription = spellDescription,
            spellSchool = spellSchool,
            baseDamage = baseDamage,
            basePowerCost = baseManaCost,
            statModifier = statModifier,
            statMultiplier = statMultiplier,
            spellModel = spellModel
        )
    }
}
