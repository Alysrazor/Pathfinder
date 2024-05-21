package com.sercapcab.pathfinder.game.exceptions

import com.sercapcab.pathfinder.game.exceptions.account.AccountAlreadyExistsException
import com.sercapcab.pathfinder.game.exceptions.account.AccountNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleException(ex: Exception): ResponseEntity<Map<String, Any>> {
        val response: MutableMap<String, Any> = mutableMapOf()
        val status: HttpStatus

        when (ex) {
            is AccountNotFoundException -> {
                response["message"] = "Account doesn't exists."
                status = HttpStatus.NOT_FOUND
            }
            is AccountAlreadyExistsException -> {
                response["message"] = "Account with that username or email already exists."
                status = HttpStatus.BAD_REQUEST
            }
            is BadCredentialsException -> {
                response["message"] = "Wrong username or password"
                status = HttpStatus.UNAUTHORIZED
            }
            is UsernameNotFoundException -> {
                response["message"] = "Username is not valid."
                status = HttpStatus.UNAUTHORIZED
            }
            else -> {
                response["message"] = "Unhandled exception occurred."
                status = HttpStatus.INTERNAL_SERVER_ERROR
            }
        }

        response["error"] = ex.message ?: "No message available"
        response["status"] = "${status.value()} - ${status.name}"

        return ResponseEntity<Map<String, Any>>(response, status)
    }
}