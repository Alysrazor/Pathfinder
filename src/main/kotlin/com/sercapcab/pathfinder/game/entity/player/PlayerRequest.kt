package com.sercapcab.pathfinder.game.entity.player

import com.sercapcab.pathfinder.game.entity.role.Role
import com.sercapcab.pathfinder.game.entity.role.RoleService
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

data class PlayerRequest(
    var playerName: String,
    var password: String,
    var androidVersion: Int = 9,
    var playerRole: String
) {
    fun toPlayer(@Autowired roleService: RoleService): Player {
        return Player(
            uuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Player".toByteArray()), playerName),
            playerName = playerName,
            password = BCryptPasswordEncoder().encode(password),
            androidVersion = androidVersion,
            playerRole = roleService.findByRoleName(playerRole).get()
        )
    }
}
