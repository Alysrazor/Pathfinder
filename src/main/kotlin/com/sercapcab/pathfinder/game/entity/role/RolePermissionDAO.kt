package com.sercapcab.pathfinder.game.entity.role

import org.springframework.data.repository.CrudRepository
import java.util.*

interface RolePermissionDAO: CrudRepository<RolePermission, Int> {
    fun findByRolePermission(permission: String): Optional<RolePermission>
}