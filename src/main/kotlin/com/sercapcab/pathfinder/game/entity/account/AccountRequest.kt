package com.sercapcab.pathfinder.game.entity.account

import com.sercapcab.pathfinder.game.security.generateUUIDv5
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

data class AccountRequest(
    val username: String,
    val email: String,
    val password: String,
) {
    fun toAccount(): Account {
        return Account(
            accountUuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Account".toByteArray())),
            username = username,
            email = email,
            password = BCryptPasswordEncoder().encode(password)
        )
    }
}
