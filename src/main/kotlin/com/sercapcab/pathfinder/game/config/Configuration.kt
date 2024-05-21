package com.sercapcab.pathfinder.game.config

import com.sercapcab.pathfinder.game.entity.account.AccountRequest
import com.sercapcab.pathfinder.game.entity.account.AccountService
import com.sercapcab.pathfinder.game.entity.role.Role
import com.sercapcab.pathfinder.game.entity.role.RoleEnum
import com.sercapcab.pathfinder.game.entity.role.RoleService
import com.sercapcab.pathfinder.game.security.MyUserDetailsService
import org.jetbrains.annotations.NotNull
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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

            val alysrazorAccount = AccountRequest(
                username = "Alysrazor",
                email = "alysrazor@rpgduels.es",
                password = "alysrazor",
            )

            val androidAccount = AccountRequest(
                username = "Android",
                email = "android@android.15",
                password = "android",
            )

            //accountService.save(alysrazorAccount.toAccount())
            //accountService.save(androidAccount.toAccount())

            //println(generateRandomSecret(32))
            val details = userDetailsService.loadUserByUsername("Alysrazor")
            println(details)
        }
    }
}
