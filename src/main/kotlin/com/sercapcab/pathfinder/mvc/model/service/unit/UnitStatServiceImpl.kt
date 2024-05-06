package com.sercapcab.pathfinder.mvc.model.service.unit

import com.sercapcab.pathfinder.mvc.model.dao.UnitStatDAO
import com.sercapcab.pathfinder.mvc.model.entity.UnitStat
import com.sercapcab.pathfinder.mvc.model.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UnitStatServiceImpl @Autowired constructor(private val unitStatDAO: UnitStatDAO): UnitStatService {

    override fun findAll(): List<UnitStat> {
       return unitStatDAO.findAll().toList()
    }

    override fun findByUUID(unitUUID: UUID): UnitStat? {
        return unitStatDAO.findById(unitUUID)
            .orElseThrow { EntityNotFoundException(UnitStat::class.java, unitUUID) }
    }

    override fun save(unitStat: UnitStat) {
        unitStatDAO.save(unitStat)
    }

    override fun delete(unitStat: UnitStat) {
        unitStatDAO.delete(unitStat)
    }

    override fun delete(unitUUID: UUID) {
        unitStatDAO.deleteById(unitUUID)
    }
}
