package com.sercapcab.pathfinder.game.entity.role

data class RoleRequest(
    var roleName: String,
    var rolePermissions: Set<RolePermission>
) {
    fun toRole(): Role {
        return Role(
            roleName = roleName,
            rolePermissions = rolePermissions
        )
    }
}