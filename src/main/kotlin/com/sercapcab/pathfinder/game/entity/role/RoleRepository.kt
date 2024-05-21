package com.sercapcab.pathfinder.game.entity.role

import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, Long> {
    fun findByRoleName(roleName: RoleEnum): Role?
}
