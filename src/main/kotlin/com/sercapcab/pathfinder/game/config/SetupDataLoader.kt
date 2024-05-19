package com.sercapcab.pathfinder.game.config

import com.sercapcab.pathfinder.game.entity.player.PlayerDAO
import com.sercapcab.pathfinder.game.entity.player.PlayerRequest
import com.sercapcab.pathfinder.game.entity.role.*
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.crypto.password.PasswordEncoder
import kotlin.jvm.optionals.getOrNull

class SetupDataLoader @Autowired constructor(
    private val playerDAO: PlayerDAO,
    private val roleDAO: RoleDAO,
    private val rolePermissionDAO: RolePermissionDAO,
    private val passwordEncoder: PasswordEncoder
): ApplicationListener<ContextRefreshedEvent> {
    var alreadySetup = false

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (alreadySetup) return

        val readGameDataPermission = createRolePermissionIfNotFound(
            "READ_GAME_DATA"
        )
        val readPlayerDataPermission = createRolePermissionIfNotFound(
            "READ_PLAYER_DATA"
        )

        val adminPermissions = setOf(readGameDataPermission, readPlayerDataPermission)
        val userPermissions = setOf(readPlayerDataPermission)
        createRoleIfNotFound(
            "ROLE_ADMIN",
            adminPermissions
        )
        createRoleIfNotFound(
            "ROLE_USER",
            userPermissions
        )

        val adminRole = roleDAO.findByRoleName("ROLE_ADMIN")
        val userRole = roleDAO.findByRoleName("ROLE_USER")

        val player = PlayerRequest(
            playerName = "Alysrazor",
            password = "alysrazor",
            playerRole = adminRole.get()
        )
        val player2 = PlayerRequest(
            playerName = "Android",
            password = "android",
            playerRole = userRole.get()
        )

        alreadySetup = true
    }

    @Transactional
    fun createRolePermissionIfNotFound(permissionName: String): RolePermission {
        var permission: RolePermission? = rolePermissionDAO.findByRolePermission(permissionName).getOrNull()

        if (permission == null) {
            permission = RolePermissionRequest(rolePermission = permissionName).toRolePermission()
            rolePermissionDAO.save(permission)
        }

        return permission
    }

    @Transactional
    fun createRoleIfNotFound(roleName: String,
                             permissions: Set<RolePermission>): Role {
        var role: Role? = roleDAO.findByRoleName(roleName).getOrNull()

        if (role == null) {
            role = RoleRequest(
                roleName = roleName,
                rolePermissions = permissions.toSet()
            ).toRole()

            roleDAO.save(role)
        }

        return role
    }
}