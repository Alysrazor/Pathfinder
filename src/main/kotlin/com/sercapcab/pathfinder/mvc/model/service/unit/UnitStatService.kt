package com.sercapcab.pathfinder.mvc.model.service.unit

import com.sercapcab.pathfinder.mvc.model.entity.UnitStat
import java.util.*

interface UnitStatService {
    fun findAll(): List<UnitStat>
    fun findByUUID(unitUUID: UUID): UnitStat?
    fun save(unitStat: UnitStat)
    fun delete(unitStat: UnitStat)
    fun delete(unitUUID: UUID)
}
