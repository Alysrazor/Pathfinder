package com.sercapcab.pathfinder.game.entity.unit

import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UnitServiceImpl @Autowired constructor(private val unitDAO: UnitDAO): UnitService {
    override fun findAll(): List<Unit> {
        return unitDAO.findAll().toList()
    }

    override fun findByUUID(unitUUID: UUID): Unit {
        return unitDAO.findById(unitUUID)
            .orElseThrow { EntityNotFoundException(Unit::class.java, unitUUID) }
    }

    override fun save(unit: Unit): Unit {
        return unitDAO.save(unit)
    }

    override fun delete(unit: Unit) {
        unitDAO.delete(unit)
    }

    override fun delete(unitUUID: UUID) {
        unitDAO.deleteById(unitUUID)
    }
}