package com.sercapcab.pathfinder.game.entity.player

import org.springframework.data.repository.CrudRepository
import java.util.*

interface PlayerDAO: CrudRepository<Player, UUID> {
    fun findByPlayerName(playerName: String): Optional<Player>
}