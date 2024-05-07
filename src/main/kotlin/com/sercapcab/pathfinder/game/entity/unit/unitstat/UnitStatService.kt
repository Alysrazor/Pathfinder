package com.sercapcab.pathfinder.game.entity.unit.unitstat

import java.util.*

interface UnitStatService {
    fun findAll(): List<UnitStat>
    fun findByUUID(unitUUID: UUID): UnitStat?
    fun save(unitStat: UnitStat)
    fun delete(unitStat: UnitStat)
    fun delete(unitUUID: UUID)
}
