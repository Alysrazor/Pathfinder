package com.sercapcab.pathfinder.game.entity.role

interface RoleService {
    fun findAll(): Iterable<Role>
    fun findById(roleId: Long): Role?
    fun save(role: Role)
    fun delete(role: Role)
    fun deleteById(roleId: Long)
    fun findByRoleName(roleName: RoleEnum): Role?
}
