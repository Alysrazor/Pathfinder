package com.sercapcab.pathfinder.game.exceptions.unit

import com.sercapcab.pathfinder.game.entity.unit.Unit
import com.sercapcab.pathfinder.game.entity.unit.unitstat.UnitStat
import java.util.*

/**
 * Excepción lanzada cuando una [Unit] tiene una [UnitStat] que se intenta borrar.
 */
class UnitStatHasDataException: Exception {
    constructor()

    constructor(msg: String): super(msg)

    constructor(uuid: UUID): super(String.format(Locale.getDefault(), "UnitStat: %s is being used and cannot be deleted", uuid.toString()))
}