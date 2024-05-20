package com.sercapcab.pathfinder.game.entity.role

data class RoleRequest(
    var roleName: String,
    var rolePermissions: Set<RolePermissionRequest>
) {
    fun toRole(): Role {
        return Role(
            roleName = roleName,
            rolePermissions = rolePermissions.map {
                it.toRolePermission()
            }.toSet()
        )
    }
}