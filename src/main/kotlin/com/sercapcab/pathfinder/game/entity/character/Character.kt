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
@Table(name = "characters")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Character(
    @Id
    @Column(name = "character_uuid", updatable = false)
    val characterUuid: UUID = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Character".toByteArray()), UUID.randomUUID().toString()),

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
