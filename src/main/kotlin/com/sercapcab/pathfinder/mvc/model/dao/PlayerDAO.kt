package com.sercapcab.pathfinder.mvc.model.dao

import com.sercapcab.pathfinder.mvc.model.entity.Player
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface PlayerDAO: CrudRepository<Player, UUID>