package com.sercapcab.pathfinder.game.exceptions.unit

import com.sercapcab.pathfinder.game.entity.character.Character
import com.sercapcab.pathfinder.game.entity.creature.Creature
import java.util.*

/**
 * Excepción que se lanza cuando un [Character] o [Creature] tiene como base una [Unit]
 */
class UnitHasDataException: Exception {
    constructor()

    constructor(msg: String): super(msg)

    constructor(uuid: UUID): super(String.format(Locale.getDefault(), "Unit %s has data on characters/creature and cannot be deleted", uuid.toString()))
}