package com.sercapcab.pathfinder.game.entity.unit

import java.util.*


interface UnitService {
    fun findAll(): List<Unit>
    fun findByUUID(unitUUID: UUID): Unit
    fun save(unit: Unit): Unit
    fun delete(unit: Unit)
    fun delete(unitUUID: UUID)
}
