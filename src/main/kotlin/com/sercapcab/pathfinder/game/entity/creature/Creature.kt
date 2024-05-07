package com.sercapcab.pathfinder.game.entity.creature

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.unit.Unit
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
@Table(name = "creature_template")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Creature(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val creatureUuid: UUID,

    @ManyToOne
    @JoinColumn(name = "unit_uuid",
        foreignKey = ForeignKey(name = "fk_unit_template"),
        nullable = false,
        updatable = false)
    val unit: Unit,

    var isBoss: Boolean
)
