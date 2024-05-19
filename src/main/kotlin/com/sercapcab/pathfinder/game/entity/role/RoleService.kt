package com.sercapcab.pathfinder.game.entity.role

import java.util.*

interface RoleService {
    fun findAll(): List<Role>
    fun findById(id: Int): Optional<Role>
    fun findByRoleName(roleName: String): Optional<Role>
    fun save(role: Role)
    fun delete(role: Role)
    fun delete(id: Int)
}