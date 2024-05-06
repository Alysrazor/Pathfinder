package com.pathfinder.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
@Table(name = "spell_template")
@AllArgsConstructor
@NoArgsConstructor
@Data
data class Spell(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val spellUUID: UUID,

    @Column(name = "spell_name")
    private var spellName: String,

    @Column(name = "spell_school")
    private var spellSchool: Int,

    @Column(name = "base_damage")
    private var baseDamage: Int,

    @Column(name = "stat_modifier")
    private var statModifier: Int,

    @Column(name = "stat_multiplier")
    private var statMultiplier: Double,

    @ManyToMany(mappedBy = "unitSpells")
    private var units: Set<Unit> = setOf()
)
