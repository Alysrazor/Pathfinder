package com.sercapcab.pathfinder.mvc.model.dao

import com.sercapcab.pathfinder.mvc.model.entity.Unit
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UnitDAO: CrudRepository<Unit, UUID>