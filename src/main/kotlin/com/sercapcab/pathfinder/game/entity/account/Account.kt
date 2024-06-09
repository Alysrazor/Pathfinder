package com.sercapcab.pathfinder.game.entity.account

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sercapcab.pathfinder.game.entity.character.Character
import com.sercapcab.pathfinder.game.entity.role.Role
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "account")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val accountUuid: UUID,

    @Column(name = "username", nullable = false, unique = true)
    var username: String,

    @Column(name = "email", nullable = false, unique = true)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "account_locked")
    var accountLocked: Boolean = false,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    @JsonIgnore
    val characters: Set<Character>? = emptySet(),

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(name = "account_access",
        joinColumns = [JoinColumn(name = "account_uuid")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableSet<Role> = mutableSetOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        if (accountUuid != other.accountUuid) return false
        if (username != other.username) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = accountUuid.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }

    override fun toString(): String {
        return String.format(Locale.getDefault(),
            "Datos de la cuenta:" +
                    "UUID: %s%n" +
                    "Usuario: %s%n" +
                    "Email: %s%n" +
                    "¿Está sancionada?: %b%n",
            this.accountUuid,
            this.username,
            this.email,
            this.accountLocked)
    }
}
