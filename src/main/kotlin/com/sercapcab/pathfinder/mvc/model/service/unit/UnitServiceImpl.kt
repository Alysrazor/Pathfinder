package com.sercapcab.pathfinder.mvc.model.service.unit

import com.sercapcab.pathfinder.mvc.model.dao.UnitDAO
import com.sercapcab.pathfinder.mvc.model.entity.Unit
import com.sercapcab.pathfinder.mvc.model.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class UnitServiceImpl @Autowired constructor(private val unitDAO: UnitDAO): UnitService {
    override fun findAll(): List<Unit> {
        return unitDAO.findAll().toList()
    }

    override fun findByUUID(unitUUID: UUID): Unit? {
        return unitDAO.findById(unitUUID)
            .orElseThrow { EntityNotFoundException(Unit::class.java, unitUUID) }
    }

    override fun save(unit: Unit) {
        unitDAO.save(unit)
    }

    override fun delete(unit: Unit) {
        unitDAO.delete(unit)
    }

    override fun delete(unitUUID: UUID) {
        unitDAO.deleteById(unitUUID)
    }
}