package com.sercapcab.pathfinder.mvc.model.service.player

import com.sercapcab.pathfinder.mvc.model.dao.PlayerDAO
import com.sercapcab.pathfinder.mvc.model.entity.Player
import com.sercapcab.pathfinder.mvc.model.exceptions.EntityNotFoundException
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