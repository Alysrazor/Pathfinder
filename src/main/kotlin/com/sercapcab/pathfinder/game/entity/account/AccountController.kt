package com.sercapcab.pathfinder.game.entity.account

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import com.sercapcab.pathfinder.game.entity.role.RoleEnum
import com.sercapcab.pathfinder.game.entity.role.RoleService
import com.sercapcab.pathfinder.game.exceptions.account.AccountAlreadyExistsException
import com.sercapcab.pathfinder.game.exceptions.account.AccountNotFoundException
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v1/account")
@Since(version = "1.0")
class AccountController constructor(
    private val accountService: AccountService,
    private val roleService: RoleService
) {
    // Getters
    @GetMapping(path = ["/"], produces = [json])
    @NotNull
    fun getAllAccounts(): ResponseEntity<List<Account>> {
        val accounts = accountService.findAll()

        return if (accounts.isEmpty())
            ResponseEntity.noContent().build()
        else
            ResponseEntity.ok(accounts)
    }

    @GetMapping(path = ["/username/{username}"], produces = [json])
    @NotNull
    fun getAccountByUsername(@PathVariable username: String): ResponseEntity<Account> {
        val account = accountService.findByUsername(username)

        return ResponseEntity.ok(account)
    }

    @GetMapping(path = ["/email/{email}"], produces = [json])
    @NotNull
    fun getAccountByEmail(@PathVariable email: String): ResponseEntity<Account> {
        val account = accountService.findByEmail(email)

        return ResponseEntity.ok(account)
    }

    // POST
    @PostMapping(path = ["/"], produces = [json])
    @NotNull
    fun create(@Valid @RequestBody account: AccountRequest): ResponseEntity<Account> {
        if (accountService.findByUsername(account.username) != null || accountService.findByEmail(account.email) != null)
            throw AccountAlreadyExistsException("Account already exists with that username or email.")

        val userRole = roleService.findByRoleName(RoleEnum.USER)!!
        val accountToSave = account.toAccount()

        accountToSave.roles = setOf(userRole)

        accountService.save(accountToSave)

        return ResponseEntity
            .created(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/")
                .buildAndExpand(account).toUri()
        )
            .body(accountToSave)
    }

    /*@PutMapping(path = ["/{usernameOrEmail}"], produces = [json])
    @NotNull
    fun updateAccount(@PathVariable usernameOrEmail: String, @Valid @RequestBody account: AccountRequest): ResponseEntity<Account> {
        val accountUsername = accountService.findByUsername(usernameOrEmail)
        val accountEmail = accountService.findByEmail(usernameOrEmail)

        if
    }*/
}
