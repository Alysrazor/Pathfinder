package com.sercapcab.pathfinder.game.entity.role

data class RolePermissionRequest(
    var rolePermission: String
) {
    fun toRolePermission(): RolePermission {
        return RolePermission(
            rolePermission = rolePermission
        )
    }
}
