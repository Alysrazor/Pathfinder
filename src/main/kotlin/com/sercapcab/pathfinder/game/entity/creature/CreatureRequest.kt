package com.sercapcab.pathfinder.game.entity.creature

import com.sercapcab.pathfinder.game.entity.unit.Unit
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import java.util.*

data class CreatureRequest(
    val unit: Unit,
    var isBoss: Boolean
) {
    fun toCreature(): Creature {
        return Creature(
            creatureUuid = generateUUIDv5(
                UUID.nameUUIDFromBytes("Game.Entity.Creature".toByteArray()),
                UUID.randomUUID().toString()
            ),
            unit = unit,
            isBoss = isBoss
        )
    }
}
