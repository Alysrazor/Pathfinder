package com.sercapcab.pathfinder.game.entity.role

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sercapcab.pathfinder.Since
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Table(name = "role_permission", catalog = "rpg_duels")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class RolePermission(
    @Id
    @Column(name = "permission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val rolePermissionId: Long? = null,

    @Column(name = "permission")
    val rolePermission: String,

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "rolePermissions")
    @JsonIgnore
    val roleId: List<Role> = listOf()
)
