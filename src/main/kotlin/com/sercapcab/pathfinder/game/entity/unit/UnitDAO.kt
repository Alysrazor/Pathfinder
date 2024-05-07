package com.sercapcab.pathfinder.game.entity.unit

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UnitDAO: CrudRepository<Unit, UUID>