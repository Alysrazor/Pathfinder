package com.sercapcab.pathfinder.game.entity.account

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface AccountRepository: CrudRepository<Account, UUID> {
    fun findByUsername(username: String): Account?
    fun findByEmail(email: String): Account?
}