package com.sercapcab.pathfinder.game.entity.character

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.unit.Unit
import com.sercapcab.pathfinder.game.entity.player.Player
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
@Table(name = "characters")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Character(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val characterUuid: UUID,

    @ManyToOne
    @JoinColumn(name = "unit_uuid",
        foreignKey = ForeignKey(name = "fk_unit_template"),
        nullable = false,
        updatable = false)
    val unit: Unit,

    @ManyToOne
    @JoinColumn(name = "player_uuid",
        foreignKey = ForeignKey(name = "fk_player"),
        nullable = false,
        updatable = false)
    val player: Player
)
