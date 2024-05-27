package com.sercapcab.pathfinder.game.entity.unitstat

import com.sercapcab.pathfinder.game.entity.character.Character
import com.sercapcab.pathfinder.game.entity.creature.Creature
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import java.util.*

data class UnitStatRequest(
    var strength: Int,
    var dexterity: Int,
    var constitution: Int,
    var intelligence: Int,
    var wisdom: Int,
    var charisma: Int,
    var comment: String,
) {
    fun toUnitStat(): UnitStat {
        return UnitStat(
            uuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.UnitStat".toByteArray())),
            strength = strength,
            dexterity = dexterity,
            constitution = constitution,
            intelligence = intelligence,
            wisdom = wisdom,
            charisma = charisma,
            comment = comment
        )
    }
}
