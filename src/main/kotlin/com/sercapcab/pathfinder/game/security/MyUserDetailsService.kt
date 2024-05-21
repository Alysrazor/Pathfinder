package com.sercapcab.pathfinder.game.security

import com.sercapcab.pathfinder.game.entity.account.AccountRepository
import com.sercapcab.pathfinder.game.exceptions.account.AccountNotFoundException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService(private val accountRepository: AccountRepository): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val account = accountRepository.findByUsername(username!!)
            ?: throw AccountNotFoundException("Account with username $username doesn't exists")

        val authorities: Set<GrantedAuthority> = account.roles.map {
            role -> SimpleGrantedAuthority("ROLE_" + role.roleName.name)
        }.toSet()

        return User(
            account.username,
            account.password,
            true,
            true,
            true,
            !account.accountLocked,
            authorities
        )
    }
}