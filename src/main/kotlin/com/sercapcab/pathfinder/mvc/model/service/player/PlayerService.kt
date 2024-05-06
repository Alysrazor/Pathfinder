package com.sercapcab.pathfinder.mvc.model.service.player

import com.sercapcab.pathfinder.mvc.model.entity.Player
import java.util.*

interface PlayerService {
    fun findAll(): List<Player>
    fun findByUUID(playerUUID: UUID): Player?
    fun save(player: Player)
    fun delete(player: Player)
    fun delete(playerUUID: UUID)
}
