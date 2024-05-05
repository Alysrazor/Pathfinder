package com.pathfinder.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
@Table(name = "unit_template")
@AllArgsConstructor
@NoArgsConstructor
@Data
data class Unit(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val uuid: UUID,

    @Column(name = "name")
    private var name: String,

    @Column(name = "level")
    private var level: UInt,

    @Column(name = "unit_armor")
    private var unitArmor: Int,

    @Column(name = "unit_magic_resistance")
    private var unitMagicResistance: Int,

    // TODO(UnitClass, UnitStats, UnitSpells)

    @Column(name = "comment")
    private var comment: String
)
