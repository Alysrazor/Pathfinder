package com.sercapcab.pathfinder.game.config

import com.sercapcab.pathfinder.game.entity.account.Account
import com.sercapcab.pathfinder.game.entity.account.AccountRequest
import com.sercapcab.pathfinder.game.entity.account.AccountService
import com.sercapcab.pathfinder.game.entity.role.Role
import com.sercapcab.pathfinder.game.entity.role.RoleEnum
import com.sercapcab.pathfinder.game.entity.role.RoleService
import com.sercapcab.pathfinder.game.security.MyUserDetailsService
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import org.jetbrains.annotations.NotNull
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

//import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class Configurator {
    @Bean
    @NotNull
    fun commandLineRunner(
        accountService: AccountService,
        roleService: RoleService,
        userDetailsService: MyUserDetailsService
    ): CommandLineRunner {
        return CommandLineRunner { _ ->
            val adminRole = Role(
                roleId = 1,
                roleName = RoleEnum.ADMIN
            )
            val developerRole = Role(
                roleId = 2,
                roleName = RoleEnum.DEVELOPER
            )
            val userRole = Role(
                roleId = 3,
                roleName = RoleEnum.USER
            )

            //roleService.save(adminRole)
            //roleService.save(developerRole)
            //roleService.save(userRole)

            val alysrazorAccount = Account(
                accountUuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Account".toByteArray())),
                username = "Alysrazor",
                email = "alysrazor@rpgduels.es",
                password = BCryptPasswordEncoder().encode("alysrazor"),
                roles = mutableSetOf(adminRole, developerRole, userRole)
            )

            val androidAccount = Account(
                accountUuid = generateUUIDv5(UUID.nameUUIDFromBytes("Game.Entity.Account".toByteArray())),
                username = "Android",
                email = "android@android.15",
                password = BCryptPasswordEncoder().encode("android"),
                roles = mutableSetOf(developerRole, userRole)
            )

            //accountService.save(alysrazorAccount)
            //accountService.save(androidAccount)

            //println(generateRandomSecret(32))
            val details = userDetailsService.loadUserByUsername("Alysrazor")
            println(details)
            println(alysrazorAccount.roles)
        }
    }
}
