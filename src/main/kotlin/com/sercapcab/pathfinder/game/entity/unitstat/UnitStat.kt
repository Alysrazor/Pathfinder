package com.sercapcab.pathfinder.game.entity.unitstat

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.character.Character
import com.sercapcab.pathfinder.game.entity.creature.Creature
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.*

@Entity
@Table(name = "unit_stat_template", catalog = "rpg_duels")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class UnitStat(
    @Id
    @Column(name = "uuid", updatable = false)
    val uuid: UUID,

    @Column(name = "strength")
    var strength: Int,

    @Column(name = "dexterity")
    var dexterity: Int,

    @Column(name = "constitution")
    var constitution: Int,

    @Column(name = "intelligence")
    var intelligence: Int,

    @Column(name = "wisdom")
    var wisdom: Int,

    @Column(name = "charisma")
    var charisma: Int,

    @Column(name = "comment")
    var comment: String,

    @OneToMany(
        mappedBy = "characterStat",
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    var characters: MutableSet<Character> = mutableSetOf(),

    @OneToMany(
        mappedBy = "creatureStats",
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    var creatures: MutableSet<Creature> = mutableSetOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UnitStat

        return uuid == other.uuid
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }

    override fun toString(): String {
        return String.format(
            "UnitStat: %s%n" +
                    "Strength: %d%n" +
                    "Dexterity: %d%n" +
                    "Constitution: %d%n" +
                    "Intelligence: %d%n" +
                    "Wisdom: %d%n" +
                    "Charisma: %d%n" +
                    "Comment: %s%n",
            uuid.toString(),
            strength,
            dexterity,
            constitution,
            intelligence,
            wisdom,
            charisma,
            comment
        )
    }
}