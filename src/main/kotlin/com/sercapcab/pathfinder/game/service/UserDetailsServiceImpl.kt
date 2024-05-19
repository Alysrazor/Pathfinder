package com.sercapcab.pathfinder.game.service

import com.sercapcab.pathfinder.game.entity.player.PlayerDAO
import com.sercapcab.pathfinder.game.entity.player.PlayerService
import com.sercapcab.pathfinder.game.entity.role.Role
import com.sercapcab.pathfinder.game.entity.role.RoleDAO
import com.sercapcab.pathfinder.game.entity.role.RolePermission
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull
import kotlin.reflect.jvm.internal.EmptyContainerForLocal.tryGetConstructor

@Service("userDetailsService")
@Transactional
class MyUserDetailsService @Autowired constructor(
    private val playerDAO: PlayerDAO,
    private val playerService: PlayerService,
    private val roleDAO: RoleDAO
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val player = playerDAO.findByPlayerName(username!!).getOrNull()
        val playerRole = player!!.playerRole

        return User(
            "",
            "",
            true,
            true,
            true,
            true,
            getAuthorities(playerRole)
        )
    }

    private fun getAuthorities(role: Set<Role>): Set<GrantedAuthority> {
        return getGrantedAuthorities(getPermissions(role))
    }

    private fun getPermissions(role: Set<Role>): Set<String> {
        val permissions: MutableSet<String> = mutableSetOf()
        val permissionCollection: MutableSet<RolePermission> = mutableSetOf()

        role.forEach {
            permissions.add(it.roleName)
            permissionCollection.addAll(it.rolePermissions)
        }

        permissionCollection.forEach {
            permissions.add(it.rolePermission)
        }

        return permissions
    }

    private fun getGrantedAuthorities(
        permissions: Set<String>
    ): Set<GrantedAuthority> {
        val authorities: MutableSet<GrantedAuthority> = mutableSetOf()

        permissions.forEach { permission ->
            authorities.add(SimpleGrantedAuthority(permission))
        }

        return authorities
    }
}

//@Service
//class UserDetailsServiceImpl @Autowired constructor(private val playerDAO: PlayerDAO) : UserDetailsService {
//    override fun loadUserByUsername(username: String?): UserDetails? {
//        val player: Player = playerDAO.findByPlayerName(username!!)
//            .orElseThrow { EntityNotFoundException(Player::class.java, username) }
//
//        val authorityList: MutableList<SimpleGrantedAuthority> = mutableListOf()
//        authorityList.add(SimpleGrantedAuthority(player.playerRole.roleName))
//
//        player.playerRole.rolePermissions.forEach { permission ->
//            authorityList.add(SimpleGrantedAuthority(permission.rolePermission))
//        }
//
//        // Log the username and authorities
//        println("User: ${player.playerName}, Authorities: ${authorityList.joinToString { it.authority }}")
//
//        return User(player.playerName, player.password, true, true, true, true, authorityList)
//    }
//}
