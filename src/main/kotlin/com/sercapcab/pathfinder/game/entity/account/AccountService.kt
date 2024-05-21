package com.sercapcab.pathfinder.game.entity.account

import com.sercapcab.pathfinder.game.exceptions.account.AccountNotFoundException
import org.jetbrains.annotations.NotNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface AccountService {
    fun findAll(): List<Account>
    fun findByUUID(accountUUID: UUID): Account?
    fun findByUsername(username: String): Account?
    fun findByEmail(email: String): Account?
    fun save(account: Account)
    fun delete(account: Account)
    fun delete(accountUUID: UUID)
}