package com.sercapcab.pathfinder.game.entity.account

import com.sercapcab.pathfinder.game.exceptions.account.AccountNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl constructor(@Autowired private val accountRepository: AccountRepository): AccountService {
    override fun findAll(): List<Account> {
        return accountRepository.findAll().toList()
    }

    override fun findByUUID(accountUUID: UUID): Account? {
        return accountRepository.findById(accountUUID).orElseThrow {
            AccountNotFoundException("Account with UUID: $accountUUID not found.")
        }
    }

    override fun findByUsername(username: String): Account? {
        return accountRepository.findByUsername(username)
    }

    override fun findByEmail(email: String): Account? {
        return accountRepository.findByEmail(email)
    }

    override fun save(account: Account) {
        accountRepository.save(account)
    }

    override fun delete(account: Account) {
        accountRepository.delete(account)
    }

    override fun delete(accountUUID: UUID) {
        accountRepository.deleteById(accountUUID)
    }
}
