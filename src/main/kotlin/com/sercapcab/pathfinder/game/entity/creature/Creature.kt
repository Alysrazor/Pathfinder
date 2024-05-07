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
@Table(name = "creature_template")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Creature(
    @Id
    @Column(name = "creature_uuid")
    val creatureUuid: UUID = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Creature".toByteArray()), UUID.randomUUID().toString()),

    @ManyToOne
    @JoinColumn(name = "unit_uuid",
        foreignKey = ForeignKey(name = "fk_unit_template"),
        nullable = false,
        updatable = false)
    val unit: Unit,

    @Column(name = "isBoss")
    var isBoss: Boolean = false
)
