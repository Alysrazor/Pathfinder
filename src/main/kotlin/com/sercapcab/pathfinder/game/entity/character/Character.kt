package com.sercapcab.pathfinder.game.entity.character

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.unit.Unit
import com.sercapcab.pathfinder.game.entity.player.Player
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
@Table(name = "characters", catalog = "rpg_duels")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Character(
    @Id
    @Column(name = "character_uuid", updatable = false)
    val characterUuid: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_uuid",
        foreignKey = ForeignKey(name = "fk_unit_template"),
        nullable = false,
        updatable = false)
    val unit: Unit,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_uuid",
        foreignKey = ForeignKey(name = "fk_player"),
        nullable = false,
        updatable = false)
    val player: Player
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Character

        if (characterUuid != other.characterUuid) return false

        return true
    }

    override fun hashCode(): Int {
        return characterUuid.hashCode()
    }

    override fun toString(): String {
        return String.format(
            "Character: %s%n" +
                    "Unit: %s%n" +
                    "Player: %s%n",
            characterUuid.toString(),
            unit,
            player
        )
    }
}
