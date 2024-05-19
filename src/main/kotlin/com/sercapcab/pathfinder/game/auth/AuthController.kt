package com.sercapcab.pathfinder.game.auth

import com.sercapcab.pathfinder.Since
import lombok.AllArgsConstructor
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
@Since(version = "1.0")
class AuthController {
    @GetMapping(path = ["/get"])
    @PreAuthorize("hasAuthority('READ_GAME_DATA')")
    fun getHello(): String {
        return "Hola Spring - GET"
    }

    @PostMapping(path = ["/post"])
    @PreAuthorize("hasAuthority('WRITE_GAME_DATA') OR hasAuthority('WRITE_PLAYER_DATA')")
    fun postHello(): String {
        return "Hola Spring - POST"
    }

    @PutMapping(path = ["/put"])
    @PreAuthorize("hasAuthority('UPDATE_GAME_DATA') OR hasAuthority('UPDATE_PLAYER_DATA')")
    fun putHello(): String {
        return "Hola Spring - PUT"
    }

    @DeleteMapping(path = ["/delete"])
    @PreAuthorize("hasAuthority('DELETE_GAME_DATA') OR hasAuthority('DELETE_PLAYER_DATA')")
    fun deleteHello(): String {
        return "Hola Spring - DELETE"
    }
}