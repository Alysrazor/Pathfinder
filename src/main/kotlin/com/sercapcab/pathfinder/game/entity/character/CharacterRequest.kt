package com.sercapcab.pathfinder.game.entity.character

import com.sercapcab.pathfinder.game.entity.player.Player
import com.sercapcab.pathfinder.game.entity.unit.Unit
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import java.util.*

data class CharacterRequest(
    val unit: Unit,
    val player: Player
) {
    fun toCharacter(): Character {
        return Character(
            characterUuid = generateUUIDv5(
                UUID.nameUUIDFromBytes("Game.Entity.Character".toByteArray()),
                UUID.randomUUID().toString()
            ),
            unit = unit,
            player = player
        )
    }
}
