package com.sercapcab.pathfinder.game.entity.character

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.player.Player
import com.sercapcab.pathfinder.game.entity.spell.Spell
import com.sercapcab.pathfinder.game.entity.unitstat.UnitStat
import com.sercapcab.pathfinder.game.enumeration.UnitClass
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "characters", catalog = "rpg_duels")
@Since(version = "1.0")
data class Character(
    @Id
    @Column(name = "uuid", nullable = false, updatable = false)
    val uuid: UUID,

    @Column(name = "name", nullable = false, unique = true)
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
        nullable = false)
    var characterStat: UnitStat,


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "characters_spell",
        catalog = "rpg_duels",
        joinColumns = [
            JoinColumn(name = "character_uuid", nullable = false)
        ],
        inverseJoinColumns = [
            JoinColumn(name = "spell_uuid", nullable = false)
        ]
    )
    var characterSpells: MutableSet<Spell>,

    @Column(name = "unit_model")
    var unitModel: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_uuid",
        nullable = false,
        updatable = false)
    val player: Player
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Character

        if (uuid != other.uuid) return false
        if (player != other.player) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + player.hashCode()
        return result
    }
}
