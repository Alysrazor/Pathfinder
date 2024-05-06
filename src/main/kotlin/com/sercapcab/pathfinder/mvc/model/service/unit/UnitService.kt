package com.sercapcab.pathfinder.mvc.model.service.unit

import com.sercapcab.pathfinder.mvc.model.entity.Unit
import java.util.*


interface UnitService {
    fun findAll(): List<Unit>
    fun findByUUID(unitUUID: UUID): Unit?
    fun save(unit: Unit)
    fun delete(unit: Unit)
    fun delete(unitUUID: UUID)
}
