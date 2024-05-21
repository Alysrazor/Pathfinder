package com.sercapcab.pathfinder.game.auth

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import com.sercapcab.pathfinder.game.entity.account.AccountService
import jakarta.validation.Valid
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
@Since(version = "1.0")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val accountService: AccountService
) {
    @PostMapping(path = ["/login"], produces = [json])
    @NotNull
    fun login(@Valid @RequestBody loginDto: LoginDto): ResponseEntity<HttpStatus> {
        return try {
            val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password))

            SecurityContextHolder.getContext().authentication = authentication
            ResponseEntity.ok().build()
        } catch(ex: BadCredentialsException) {
            ResponseEntity.badRequest().build()
        } catch(ex: Exception) {
            ResponseEntity.internalServerError().build()
        }
    }
}