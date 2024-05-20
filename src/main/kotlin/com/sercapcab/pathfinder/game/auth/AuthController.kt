package com.sercapcab.pathfinder.game.auth

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import lombok.AllArgsConstructor
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
@Since(version = "1.0")
class AuthController {
    @GetMapping(path = ["/ping"], produces = [json])
    fun pong(): String {
        return "Pong!"
    }


    @GetMapping(path = ["/hello"], produces = [json])
    fun helloPublic(): String {
        return "Hola sin seguridad"
    }

    @GetMapping(path = ["/hello-secured"], produces = [json])
    fun helloPrivate(): String {
        return "Hola con seguridad"
    }
}