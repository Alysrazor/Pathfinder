package com.sercapcab.pathfinder.game.entity.role

import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RolePermissionServiceImpl @Autowired constructor(private val rolePermissionDAO: RolePermissionDAO): RolePermissionService {
    override fun findAll(): List<RolePermission> {
        return rolePermissionDAO.findAll().toList()
    }

    override fun findById(id: Int): Optional<RolePermission> {
        return Optional.ofNullable(rolePermissionDAO.findById(id))
            .orElseThrow { EntityNotFoundException(RolePermission::class.java, id.toLong()) }
    }

    override fun findByRolePermission(rolePermission: String): Optional<RolePermission> {
        return Optional.ofNullable(rolePermissionDAO.findByRolePermission(rolePermission))
            .orElseThrow { EntityNotFoundException(RolePermission::class.java, rolePermission) }
    }

    override fun save(rolePermission: RolePermission) {
        rolePermissionDAO.save(rolePermission)
    }

    override fun delete(rolePermission: RolePermission) {
        rolePermissionDAO.delete(rolePermission)
    }

    override fun delete(id: Int) {
        rolePermissionDAO.deleteById(id)
    }
}