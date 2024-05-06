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

    @Column(name = "unit_class")
    private var unitClass: Int,

    @ManyToOne
    @JoinColumn(name = "unit_stats", nullable = false)
    private var unitStats: UnitStats,

    @ManyToMany
    @JoinTable(
        name = "unit_spells",
        joinColumns = [JoinColumn(name = "unit_uuid")],
        inverseJoinColumns = [JoinColumn(name = "spell_uuid")]
    )
    private var unitSpells: Set<Spell>,

    @Column(name = "comment")
    private var comment: String,
)
