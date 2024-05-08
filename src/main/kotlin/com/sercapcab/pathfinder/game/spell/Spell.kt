package com.sercapcab.pathfinder.game.spell

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.unit.Unit
import com.sercapcab.pathfinder.game.enumeration.SpellSchool
import com.sercapcab.pathfinder.game.enumeration.Stat
import com.sercapcab.pathfinder.game.security.generateUUIDv5
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
@Since(version = "1.0")
data class Spell(
    @Id
    @Column(name = "spell_uuid", updatable = false)
    val spellUuid: UUID,

    @Column(name = "spell_name")
    var spellName: String,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "spell_school")
    var spellSchool: SpellSchool,

    @Column(name = "base_damage")
    var baseDamage: Int,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "stat_modifier")
    var statModifier: Stat,

    @Column(name = "stat_multiplier")
    var statMultiplier: Double,

    @ManyToMany(mappedBy = "unitSpells",
        fetch = FetchType.LAZY)
    var units: MutableSet<Unit> = mutableSetOf()
)
