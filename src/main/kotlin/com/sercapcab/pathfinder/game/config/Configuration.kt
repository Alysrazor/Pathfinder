package com.sercapcab.pathfinder.game.config

import com.sercapcab.pathfinder.game.entity.player.PlayerDAO
import com.sercapcab.pathfinder.game.entity.player.PlayerRequest
import com.sercapcab.pathfinder.game.entity.role.Role
import com.sercapcab.pathfinder.game.entity.role.RoleDAO
import com.sercapcab.pathfinder.game.entity.role.RolePermission
import com.sercapcab.pathfinder.game.entity.role.RolePermissionDAO
import com.sercapcab.pathfinder.game.security.generateRandomSecret
import com.sercapcab.pathfinder.game.service.UserDetailsServiceImpl
import org.jetbrains.annotations.NotNull
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder

@Configuration
class Configurator {
    @Bean
    @NotNull
    fun commandLineRunner(playerDAO: PlayerDAO,
                          roleDAO: RoleDAO,
                          rolePermissionDAO: RolePermissionDAO,
                          userDetailsServiceImpl: UserDetailsServiceImpl
    ): CommandLineRunner {
        return CommandLineRunner { _ ->
            println(generateRandomSecret(32))

            /* PERMISSIONS */
            val readGameDataPermission = RolePermission(
                rolePermission = "READ_GAME_DATA"
            )
            val readPlayerDataPermission = RolePermission(
                rolePermission = "READ_PLAYER_DATA"
            )
            val writeGameData = RolePermission(
                rolePermission = "WRITE_GAME_DATA"
            )
            val writePlayerData = RolePermission(
                rolePermission = "WRITE_PLAYER_DATA"
            )
            val updateGameData = RolePermission(
                rolePermission = "UPDATE_GAME_DATA"
            )
            val updatePlayerData = RolePermission(
                rolePermission = "UPDATE_PLAYER_DATA"
            )
            val deleteGameData = RolePermission(
                rolePermission = "DELETE_GAME_DATA"
            )
            val deletePlayerData = RolePermission(
                rolePermission = "DELETE_PLAYER_DATA"
            )

            /* ROLES */
            val adminRole = Role(
                roleName = "ROLE_ADMINISTRATOR",
                rolePermissions = listOf(
                    readGameDataPermission,
                    readPlayerDataPermission,
                    writeGameData,
                    writePlayerData,
                    updateGameData,
                    updatePlayerData,
                    deleteGameData,
                    deletePlayerData
                )
            )

            val userRole = Role(
                roleName = "ROLE_USER",
                rolePermissions = listOf(
                    readPlayerDataPermission,
                    writePlayerData,
                    updatePlayerData,
                    deletePlayerData
                )
            )

            val player = PlayerRequest(
                playerName = "Alysrazor",
                password = "alysrazor",
                androidVersion = 13,
                playerRole = adminRole
            )

            val player2 = PlayerRequest(
                playerName = "Android",
                password = "android",
                androidVersion = 15,
                playerRole = userRole
            )

//            rolePermissionDAO.saveAll(mutableSetOf(writeGameData, writePlayerData, readGameDataPermission, readPlayerDataPermission, updateGameData, updatePlayerData, deletePlayerData, deleteGameData))
//            roleDAO.saveAll(mutableSetOf(adminRole, userRole))
//            playerDAO.saveAll(mutableSetOf(player.toPlayer(), player2.toPlayer()))
        }
    }
}
