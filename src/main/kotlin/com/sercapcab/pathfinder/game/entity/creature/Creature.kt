package com.sercapcab.pathfinder.game.entity.creature

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.unitstat.UnitStat
import com.sercapcab.pathfinder.game.enumeration.UnitClass
import com.sercapcab.pathfinder.game.spell.Spell
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.*

@Entity
@Table(name = "creature_template", catalog = "rpg_duels")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Creature(
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
    @JoinColumn(
        name = "unit_stats",
        foreignKey = ForeignKey(name = "fk_unit_stat"),
        nullable = false
    )
    var creatureStats: UnitStat,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "creature_spell",
        catalog = "rpg_duels",
        joinColumns = [
            JoinColumn(name = "creature_uuid", nullable = false)
        ],
        inverseJoinColumns = [
            JoinColumn(name = "spell_uuid", nullable = false)
        ]
    )
    var creatureSpells: MutableSet<Spell> = mutableSetOf(),

    @Column(name = "unit_model")
    var unitModel: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Creature

        if (uuid != other.uuid) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}
