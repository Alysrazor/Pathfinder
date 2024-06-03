package com.sercapcab.pathfinder.game.entity.unitstat

import org.springframework.data.repository.CrudRepository
import java.util.*

interface UnitStatRepository: CrudRepository<UnitStat, UUID> {
    fun findByComment(comment: String): UnitStat?
}