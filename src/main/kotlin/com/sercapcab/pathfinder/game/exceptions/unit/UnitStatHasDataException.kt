package com.sercapcab.pathfinder.game.exceptions.unit

import com.sercapcab.pathfinder.game.entity.unitstat.UnitStat
import com.sercapcab.pathfinder.game.entity.character.Character
import com.sercapcab.pathfinder.game.entity.creature.Creature
import java.util.*

/**
 * Excepción lanzada cuando una [UnitStat] que se intenta borrar es usada por [Character] o [Creature].
 */
class UnitStatHasDataException: Exception {
    constructor()

    constructor(msg: String): super(msg)

    constructor(uuid: UUID): super(String.format(Locale.getDefault(), "UnitStat: %s is being used and cannot be deleted", uuid.toString()))
}