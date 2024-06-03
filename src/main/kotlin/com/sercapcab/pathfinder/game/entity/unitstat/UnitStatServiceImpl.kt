package com.sercapcab.pathfinder.game.entity.unitstat

import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UnitStatServiceImpl @Autowired constructor(private val unitStatRepository: UnitStatRepository): UnitStatService {

    override fun findAll(): List<UnitStat> {
       return unitStatRepository.findAll().toList()
    }

    override fun findByUUID(unitUUID: UUID): UnitStat {
        return unitStatRepository.findById(unitUUID)
            .orElseThrow { EntityNotFoundException(UnitStat::class.java, unitUUID) }
    }

    override fun findByComment(comment: String): UnitStat? {
        return unitStatRepository.findAll().firstOrNull { it.comment.contains(Regex(comment)) }
    }

    override fun save(unitStat: UnitStat): UnitStat {
        return unitStatRepository.save(unitStat)
    }

    override fun delete(unitStat: UnitStat) {
        unitStatRepository.delete(unitStat)
    }

    override fun delete(unitUUID: UUID) {
        unitStatRepository.deleteById(unitUUID)
    }
}
