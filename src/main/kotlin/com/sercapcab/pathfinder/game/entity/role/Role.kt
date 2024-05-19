package com.sercapcab.pathfinder.game.entity.role

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.player.Player
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor


@Entity
@Table(name = "role", catalog = "rpg_duels")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val roleId: Long? = null,

    @Column(name = "role_name", unique = true, nullable = false)
    var roleName: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "role_has_permission",
        catalog = "rpg_duels",
        joinColumns = [
            JoinColumn(name = "role_id", nullable = false)
        ],
        inverseJoinColumns = [
            JoinColumn(name = "permission_id", nullable = false)
        ]
    )
    var rolePermissions: Set<RolePermission> = setOf(),

    @OneToMany(fetch = FetchType.LAZY,
        mappedBy = "playerRole")
    @JsonIgnore
    val players: List<Player> = listOf()
)
