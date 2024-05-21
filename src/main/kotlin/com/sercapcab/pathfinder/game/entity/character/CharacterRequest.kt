package com.sercapcab.pathfinder.game.entity.character

import com.sercapcab.pathfinder.game.entity.account.Account
import com.sercapcab.pathfinder.game.entity.spell.Spell
import com.sercapcab.pathfinder.game.entity.unitstat.UnitStat
import com.sercapcab.pathfinder.game.enumeration.UnitClass
import com.sercapcab.pathfinder.game.security.generateUUIDv5
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
    val account: Account
) {
    fun toCharacter(): Character {
        return Character(
            uuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Character".toByteArray())),
            name = name,
            level = level.coerceAtMost(10u),
            unitArmor = unitArmor,
            unitMagicResistance = unitMagicResistance,
            unitClass = unitClass,
            characterStat = characterStat,
            characterSpells = characterSpells,
            unitModel = unitModel,
            account = account
        )
    }
}
