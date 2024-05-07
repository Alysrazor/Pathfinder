package com.sercapcab.pathfinder.game.entity.player

import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class PlayerServiceImpl @Autowired constructor(private val playerDAO: PlayerDAO): PlayerService {
    override fun findAll(): List<Player> {
        return playerDAO.findAll().toList()
    }

    override fun findByUUID(playerUUID: UUID): Player? {
        return playerDAO.findById(playerUUID)
            .orElseThrow { EntityNotFoundException(Player::class.java, playerUUID) }
    }

    override fun save(player: Player) {
        playerDAO.save(player)
    }

    override fun delete(player: Player) {
        playerDAO.delete(player)
    }

    override fun delete(playerUUID: UUID) {
        playerDAO.deleteById(playerUUID)
    }
}