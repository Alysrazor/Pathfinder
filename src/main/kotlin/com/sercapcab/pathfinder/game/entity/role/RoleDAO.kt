package com.sercapcab.pathfinder.game.entity.role

import org.springframework.data.repository.CrudRepository
import java.util.*

interface RoleDAO: CrudRepository<Role, Int> {
    fun findByRoleName(roleName: String): Optional<Role>
}
