package com.sercapcab.pathfinder.game.entity.player

import java.util.*

interface PlayerService {
    fun findAll(): List<Player>
    fun findByUUID(playerUUID: UUID): Player?
    fun findByPlayerName(playerName: String): Optional<Player>
    fun save(player: Player)
    fun delete(player: Player)
    fun delete(playerUUID: UUID)
}
