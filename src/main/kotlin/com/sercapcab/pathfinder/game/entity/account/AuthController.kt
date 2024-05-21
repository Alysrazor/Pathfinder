package com.sercapcab.pathfinder.game.entity.account

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.log

@RestController
@RequestMapping("/api/v1/auth")
class AuthController constructor(
    private val authenticationMananger: AuthenticationManager
) {
    @PostMapping("/login")
    fun login(@Valid @RequestBody loginDto: AccountLoginDto): ResponseEntity<String> {
        val authentication = authenticationMananger.authenticate(UsernamePasswordAuthenticationToken(
            loginDto.username, loginDto.password
        ))

        SecurityContextHolder.getContext().authentication = authentication

        return ResponseEntity.ok("User signed-in successfully " + HttpStatus.OK.name)
    }
}