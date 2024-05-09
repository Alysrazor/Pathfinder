package com.sercapcab.pathfinder.game.spell

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.character.Character
import com.sercapcab.pathfinder.game.entity.creature.Creature
import com.sercapcab.pathfinder.game.enumeration.SpellSchool
import com.sercapcab.pathfinder.game.enumeration.Stat
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.*

@Entity
@Table(name = "spell_template", catalog = "rpg_duels")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Spell(
    @Id
    @Column(name = "spell_uuid", nullable = false, updatable = false)
    val spellUuid: UUID,

    @Column(name = "spell_name")
    var spellName: String,

    @Column(name = "spell_description")
    var spellDescription: String,

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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "characterSpells")
    @JsonIgnore
    var characters: MutableSet<Character> = mutableSetOf(),

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "creatureSpells")
    @JsonIgnore
    var creatures: MutableSet<Creature> = mutableSetOf()
) {
    override fun hashCode(): Int {
        var result = spellUuid.hashCode()
        result = 31 * result + spellName.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Spell) return false

        if (spellUuid != other.spellUuid) return false
        if (spellName != other.spellName) return false

        return true
    }

    override fun toString(): String {
        return String.format(Locale.getDefault(),
            "Hechizo: %s%n" +
                    "Nombre: %s%n" +
                    "Descripción: %s%n" +
                    "SpellSchool: %s%n" +
                    "Daño Base: %d%n" +
                    "Estadística del hechizo: %s%n" +
                    "Multiplicador de Estadística: %.2f%n",
            spellUuid.toString(),
            spellName,
            spellDescription,
            spellSchool.name,
            baseDamage,
            statModifier.statName,
            statMultiplier)
    }
}
