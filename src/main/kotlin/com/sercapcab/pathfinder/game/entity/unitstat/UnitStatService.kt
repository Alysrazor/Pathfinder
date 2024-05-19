package com.sercapcab.pathfinder.game.entity.unitstat

import java.util.*

interface UnitStatService {
    fun findAll(): List<UnitStat>
    fun findByUUID(unitUUID: UUID): UnitStat
    fun save(unitStat: UnitStat): UnitStat
    fun delete(unitStat: UnitStat)
    fun delete(unitUUID: UUID)
}