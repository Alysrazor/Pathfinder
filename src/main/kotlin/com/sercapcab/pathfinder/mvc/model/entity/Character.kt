package com.sercapcab.pathfinder.mvc.model.entity

import com.sercapcab.pathfinder.Since
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
    private val characterUuid: UUID,

    @ManyToOne
    @JoinColumn(name = "unit_uuid",
        foreignKey = ForeignKey(name = "fk_unit_template"),
        nullable = false,
        updatable = false)
    private val unit: Unit,

    @ManyToOne
    @JoinColumn(name = "player_uuid",
        foreignKey = ForeignKey(name = "fk_player"),
        nullable = false,
        updatable = false)
    private val player: Player
)
