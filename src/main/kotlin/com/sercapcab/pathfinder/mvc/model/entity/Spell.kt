package com.sercapcab.pathfinder.mvc.model.entity

import com.sercapcab.pathfinder.Since
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.rest.core.config.Projection
import java.util.UUID

@Entity
@Table(name = "spell_template")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Spell(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val spellUuid: UUID,

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

    @ManyToMany(mappedBy = "unitSpells",
        fetch = FetchType.LAZY)
    private var units: Set<Unit> = setOf()
)
