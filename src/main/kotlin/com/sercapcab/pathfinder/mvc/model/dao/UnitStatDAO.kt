package com.sercapcab.pathfinder.mvc.model.dao

import com.sercapcab.pathfinder.mvc.model.entity.UnitStat
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UnitStatDAO: CrudRepository<UnitStat, UUID>