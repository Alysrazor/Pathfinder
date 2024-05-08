package com.sercapcab.pathfinder.game.entity.creature

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.unit.Unit
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
@Table(name = "creature_template", catalog = "rpg_duels")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Creature(
    @Id
    @Column(name = "creature_uuid")
    val creatureUuid: UUID,

    @ManyToOne(fetch = FetchType.LAZY    )
    @JoinColumn(name = "unit_uuid",
        foreignKey = ForeignKey(name = "fk_unit_template"),
        nullable = false,
        updatable = false)
    val unit: Unit,

    @Column(name = "is_boss")
    var isBoss: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Creature

        if (creatureUuid != other.creatureUuid) return false

        return true
    }

    override fun hashCode(): Int {
        return creatureUuid.hashCode()
    }

    override fun toString(): String {
        return String.format(
            "Creature: %s%n" +
                    "Is Boss: %b%n",
            creatureUuid.toString(),
            isBoss
        )
    }
}
