package com.sercapcab.pathfinder.game.entity.player

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.character.Character
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private val uuid: UUID,

    @Column(name = "player_name", length = 20, nullable = false, unique = true)
    private var playerName: String,

    @Column(name = "password", length = 64, nullable = false)
    private var password: String,

    @Column(name = "salt", length = 64, nullable = false)
    private var salt: String,

    @Column(name = "android_version")
    private var androidVersion: Int,

    @OneToMany(mappedBy = "player",
        fetch = FetchType.LAZY)
    private var characters: Set<Character>
)