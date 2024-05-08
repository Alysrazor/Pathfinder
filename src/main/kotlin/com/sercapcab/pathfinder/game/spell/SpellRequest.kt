package com.sercapcab.pathfinder.game.spell

import com.sercapcab.pathfinder.game.enumeration.SpellSchool
import com.sercapcab.pathfinder.game.enumeration.Stat
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import java.util.*

data class SpellRequest(
    var spellName: String,
    var spellSchool: SpellSchool,
    var baseDamage: Int,
    var statModifier: Stat,
    var statMultiplier: Double
) {
    fun toSpell(): Spell {
        return Spell(
            spellUuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Spell".toByteArray()), UUID.randomUUID().toString()),
            spellName = spellName,
            spellSchool = spellSchool,
            baseDamage = baseDamage,
            statModifier = statModifier,
            statMultiplier = statMultiplier
        )
    }
}
