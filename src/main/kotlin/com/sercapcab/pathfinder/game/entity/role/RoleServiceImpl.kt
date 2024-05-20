package com.sercapcab.pathfinder.game.entity.role

import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoleServiceImpl @Autowired constructor(private val roleDAO: RoleDAO, private val rolePermissionDAO: RolePermissionDAO): RoleService {
    override fun findAll(): List<Role> {
        return roleDAO.findAll().toList()
    }

    override fun findById(id: Int): Optional<Role> {
        return Optional.ofNullable(roleDAO.findById(id))
            .orElseThrow { EntityNotFoundException(Role::class.java, id.toLong()) }
    }

    override fun findByRoleName(roleName: String): Optional<Role> {
        return Optional.ofNullable(roleDAO.findByRoleName(roleName))
            .orElseThrow { EntityNotFoundException(Role::class.java, roleName) }
    }

    override fun save(role: Role) {
        val rolePermissions: Set<RolePermission> = role.rolePermissions

        rolePermissions.forEach { permission ->
            if (!rolePermissionDAO.findByRolePermission(permission.rolePermission).isPresent)
                rolePermissionDAO.save(permission)
        }

        roleDAO.save(role)
    }

    override fun delete(role: Role) {
        roleDAO.delete(role)
    }

    override fun delete(id: Int) {
        roleDAO.deleteById(id)
    }
}