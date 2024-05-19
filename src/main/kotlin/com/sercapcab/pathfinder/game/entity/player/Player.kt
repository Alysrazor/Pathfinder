package com.sercapcab.pathfinder.game.entity.player

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.role.Role
import com.sercapcab.pathfinder.game.entity.character.Character
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

    @Column(name = "android_version")
    var androidVersion: Int = 9,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "role_id",
        nullable = false
    )
    var playerRole: Role,

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
                    "Rol: %s%n" +
                    "Permisos: %s%n" +
                    "Android Version: %d%n",
            uuid.toString(),
            playerName,
            password,
            playerRole,
            playerRole.rolePermissions.joinToString(separator = "\n"),
            androidVersion,
        )
    }
}