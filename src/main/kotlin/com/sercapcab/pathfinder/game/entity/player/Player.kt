package com.sercapcab.pathfinder.game.entity.player

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.character.Character
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
@Table(name = "player")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Since(version = "1.0")
data class Player(
    @Id
    @Column(name = "uuid", updatable = false)
    val uuid: UUID,

    @Column(name = "player_name", length = 20, nullable = false, unique = true)
    var playerName: String,

    @Column(name = "password", length = 64, nullable = false)
    var password: String,

    @Column(name = "salt", length = 64, nullable = false)
    var salt: String    ,

    @Column(name = "android_version")
    var androidVersion: Int = 9,

    @OneToMany(mappedBy = "player",
        fetch = FetchType.LAZY)
    var characters: Set<Character> = setOf()
)