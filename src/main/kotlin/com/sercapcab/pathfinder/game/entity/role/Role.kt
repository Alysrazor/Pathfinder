package com.sercapcab.pathfinder.game.entity.role

import jakarta.persistence.*

@Entity
@Table(name = "role")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val roleId: Long?,

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    var roleName: RoleEnum
)
