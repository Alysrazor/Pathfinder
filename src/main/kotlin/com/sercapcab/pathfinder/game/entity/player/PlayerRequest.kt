package com.sercapcab.pathfinder.game.entity.player

import com.sercapcab.pathfinder.game.security.generateUUIDv5
import com.sercapcab.pathfinder.game.security.hashPassword
import java.util.*

data class PlayerRequest(
    var playerName: String,
    var password: String,
    var androidVersion: Int = 9
) {
    fun toPlayer(): Player {
        val hashPasswordAndSalt = hashPassword(password = password)
        val hashedPassword = hashPasswordAndSalt[0]
        val salt = hashPasswordAndSalt[1]

        return Player(
            uuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Player".toByteArray()), playerName),
            playerName = playerName,
            password = hashedPassword,
            salt = salt,
            androidVersion = androidVersion
        )
    }
}
