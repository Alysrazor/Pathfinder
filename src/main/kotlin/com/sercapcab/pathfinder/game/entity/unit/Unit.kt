package com.sercapcab.pathfinder.game.entity.unit

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.character.Character
import com.sercapcab.pathfinder.game.entity.creature.Creature
import com.sercapcab.pathfinder.game.entity.unit.unitstat.UnitStat
import com.sercapcab.pathfinder.game.enumeration.UnitClass
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import com.sercapcab.pathfinder.game.spell.Spell
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable
import java.util.*

@Entity
@Table(name = "unit_template")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Unit(
    @Id
    @Column(name = "uuid", nullable = false, updatable = false)
    val uuid: UUID,

    @Column(name = "name")
    var name: String,

    @Column(name = "level")
    var level: UInt,

    @Column(name = "unit_armor")
    var unitArmor: Int,

    @Column(name = "unit_magic_resistance")
    var unitMagicResistance: Int,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "unit_class")
    var unitClass: UnitClass,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_stats",
        foreignKey = ForeignKey(name = "fk_unit_stat"),
        nullable = false)
    var unitStat: UnitStat,


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "unit_spells",
        catalog = "rpg_duels",
        joinColumns = [
            JoinColumn(name = "unit_uuid", nullable = false)
        ],
        inverseJoinColumns = [
            JoinColumn(name = "spell_uuid", nullable = false)
        ]
    )
    var unitSpells: MutableSet<Spell>,
    
    @Column(name = "unit_model")
    var unitModel: Int,

    @Column(name = "comment")
    var comment: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
    @JsonIgnore
    val characters: MutableSet<Character> = mutableSetOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
    @JsonIgnore
    val creatures: MutableSet<Creature> = mutableSetOf()
) {
    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Unit) return false

        if (uuid != other.uuid) return false
        if (name != other.name) return false

        return true
    }

    override fun toString(): String {
        val spellsString = unitSpells.joinToString("\n") { spell -> spell.toString() }
        return String.format(
            Locale.getDefault(),
            "UUID: %s%n" +
                    "Nombre: %s%n" +
                    "Nivel: %d%n" +
                    "Armadura de Unidad: %d%n" +
                    "Resistencia Mágica de Unidad: %d%n" +
                    "Clase de Unidad: %s%n" +
                    "Estadísticas de Unidad: %s%n" +
                    "Hechizos de Unidad:%n%s%n" +
                    "Modelo de Unidad: %d%n" +
                    "Comentario: %s%n",
            uuid.toString(),
            name,
            level,
            unitArmor,
            unitMagicResistance,
            unitClass,
            unitStat,
            spellsString,
            unitModel,
            comment
        )
    }
}
