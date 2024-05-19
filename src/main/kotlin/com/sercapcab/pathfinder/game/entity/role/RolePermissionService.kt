package com.sercapcab.pathfinder.game.entity.role

import java.util.*

interface RolePermissionService {
    fun findAll(): List<RolePermission>
    fun findById(id: Int): Optional<RolePermission>
    fun findByRolePermission(rolePermission: String): Optional<RolePermission>
    fun save(rolePermission: RolePermission)
    fun delete(rolePermission: RolePermission)
    fun delete(id: Int)
}