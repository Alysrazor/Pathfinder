package com.sercapcab.pathfinder.game.entity.unit

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.unit.unitstat.UnitStat
import com.sercapcab.pathfinder.game.enumeration.UnitClass
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import com.sercapcab.pathfinder.game.spell.Spell
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.*

@Entity
@Table(name = "unit_template")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Unit(
    @Id
    @Column(name = "uuid", updatable = false)
    var uuid: UUID = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Unit".toByteArray()), UUID.randomUUID().toString()),

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

    @ManyToOne
    @JoinColumn(name = "unit_stats",
        foreignKey = ForeignKey(name = "fk_unit_stat"),
        nullable = false)
    var unitStat: UnitStat,

    @ManyToMany
    @JoinTable(
        name = "unit_spells",
        joinColumns = [JoinColumn(name = "unit_uuid")],
        foreignKey = ForeignKey(name = "fk_unit_spell"),
        inverseJoinColumns = [JoinColumn(name = "spell_uuid")],
        inverseForeignKey = ForeignKey(name = "fk_spell")
    )
    var unitSpells: MutableSet<Spell> = mutableSetOf(),

    @Column(name = "unit_model")
    var unitModel: Int,

    @Column(name = "comment")
    var comment: String,
)
