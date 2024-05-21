package com.sercapcab.pathfinder.game.auth

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import com.sercapcab.pathfinder.game.entity.account.AccountRequest
import com.sercapcab.pathfinder.game.entity.account.AccountService
import com.sercapcab.pathfinder.game.entity.role.RoleEnum
import com.sercapcab.pathfinder.game.entity.role.RoleService
import com.sercapcab.pathfinder.game.exceptions.account.AccountAlreadyExistsException
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
@Since(version = "1.0")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val accountService: AccountService,
    private val roleService: RoleService
) {
    @PostMapping(path = ["/signin"], produces = [json])
    @NotNull
    fun signIn(@Valid @RequestBody loginDto: LoginDto): ResponseEntity<HttpStatus> {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password)
        )

        SecurityContextHolder.getContext().authentication = authentication
        return ResponseEntity.ok().build()
        /*return try {

        } catch (ex: BadCredentialsException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        } catch (ex: UsernameNotFoundException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        } catch (ex: Exception) {
            ResponseEntity.internalServerError().build()
        }*/
    }

    @PostMapping(path = ["/signup"], produces = [json])
    @NotNull
    fun signUp(@Valid @RequestBody account: AccountRequest): ResponseEntity<HttpStatus> {
        if (accountService.findByEmail(account.email) != null || (accountService.findByUsername(account.username) != null))
            throw AccountAlreadyExistsException("There is an account with that email already.")

        val accountToSave = account.toAccount()
        val userRole = roleService.findByRoleName(RoleEnum.USER)!!
        accountToSave.roles = setOf(userRole)

        accountService.save(accountToSave)

        return ResponseEntity.ok().build()
    }
}
