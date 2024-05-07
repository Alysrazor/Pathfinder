package com.sercapcab.pathfinder.game.entity.player

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface PlayerDAO: CrudRepository<Player, UUID>