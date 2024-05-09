package com.sercapcab.pathfinder.game.entity.character

import com.sercapcab.pathfinder.game.entity.player.Player
import com.sercapcab.pathfinder.game.entity.unitstat.UnitStat
import com.sercapcab.pathfinder.game.enumeration.UnitClass
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import com.sercapcab.pathfinder.game.spell.Spell
import java.util.*

data class CharacterRequest(
    var name: String,
    var level: UInt,
    var unitArmor: Int,
    var unitMagicResistance: Int,
    var unitClass: UnitClass,
    var characterStat: UnitStat,
    val characterSpells: MutableSet<Spell>,
    var unitModel: Int,
    val player: Player
) {
    fun toCharacter(): Character {
        return Character(
            uuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Character".toByteArray()), UUID.randomUUID().toString()),
            name = name,
            level = level.coerceAtMost(10u),
            unitArmor = unitArmor,
            unitMagicResistance = unitMagicResistance,
            unitClass = unitClass,
            characterStat = characterStat,
            characterSpells = characterSpells,
            unitModel = unitModel,
            player = player
        )
    }
}
