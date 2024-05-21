package com.sercapcab.pathfinder.game.entity.role

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl @Autowired constructor(
    private val roleRepository: RoleRepository
) : RoleService {

    override fun findAll(): Iterable<Role> {
        return roleRepository.findAll()
    }

    override fun findById(roleId: Long): Role? {
        return roleRepository.findById(roleId).orElse(null)
    }

    override fun save(role: Role) {
        roleRepository.save(role)
    }

    override fun delete(role: Role) {
        roleRepository.delete(role)
    }

    override fun deleteById(roleId: Long) {
        roleRepository.deleteById(roleId)
    }

    override fun findByRoleName(roleName: RoleEnum): Role? {
        return roleRepository.findByRoleName(roleName)
    }
}
