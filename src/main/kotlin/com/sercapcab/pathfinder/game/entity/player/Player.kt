package com.sercapcab.pathfinder.game.entity.player

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.character.Character
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
@Table(name = "player", catalog = "rpg_duels")
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
    //@JsonIgnore
    var password: String,

    @Column(name = "salt", length = 64, nullable = false)
    //@JsonIgnore
    var salt: String,

    @Column(name = "android_version")
    var androidVersion: Int = 9,

    @OneToMany(
        mappedBy = "player",
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    var characters: Set<Character> = setOf(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (uuid != other.uuid) return false
        if (playerName != other.playerName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + playerName.hashCode()
        return result
    }

    override fun toString(): String {
        return String.format(
            "Player: %s%n" +
                    "Name: %s%n" +
                    "Password: %s%n" +
                    "Salt: %s%n" +
                    "Android Version: %d%n",
            uuid.toString(),
            playerName,
            password,
            salt,
            androidVersion,
        )
    }
}