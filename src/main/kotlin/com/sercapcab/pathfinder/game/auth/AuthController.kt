package com.sercapcab.pathfinder.game.auth

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import com.sercapcab.pathfinder.game.entity.account.AccountRequest
import com.sercapcab.pathfinder.game.entity.account.AccountService
import com.sercapcab.pathfinder.game.entity.character.Character
import com.sercapcab.pathfinder.game.entity.character.CharacterRequest
import com.sercapcab.pathfinder.game.entity.character.CharacterService
import com.sercapcab.pathfinder.game.entity.role.RoleEnum
import com.sercapcab.pathfinder.game.entity.role.RoleService
import com.sercapcab.pathfinder.game.entity.spell.SpellService
import com.sercapcab.pathfinder.game.entity.unitstat.UnitStatService
import com.sercapcab.pathfinder.game.enumeration.UnitClass
import com.sercapcab.pathfinder.game.exceptions.account.AccountAlreadyExistsException
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/auth")
@Since(version = "1.0")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val accountService: AccountService,
    private val roleService: RoleService,
    private val characterService: CharacterService,
    private val spellService: SpellService,
    private val unitStatService: UnitStatService
) {
    @GetMapping(path = ["/ping"], produces = [json])
    @NotNull
    fun ping(): ResponseEntity<String> {
        return ResponseEntity.ok("Pong!")
    }

    @PostMapping(path = ["/signin"], produces = [json])
    @NotNull
    fun signIn(@Valid @RequestBody loginDto: LoginDto): ResponseEntity<HttpStatus> {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password)
        )

        SecurityContextHolder.getContext().authentication = authentication
        return ResponseEntity.ok().build()
    }

    @PostMapping(path = ["/signup"], produces = [json])
    @NotNull
    fun signUp(@Valid @RequestBody account: AccountRequest): ResponseEntity<HttpStatus> {
        if (accountService.findByEmail(account.email) != null || (accountService.findByUsername(account.username) != null))
            throw AccountAlreadyExistsException("There is an account with that email already.")
        val accountToSave = account.toAccount()

        val userRole = roleService.findByRoleName(RoleEnum.USER)!!
        accountToSave.roles.add(userRole)

        accountService.save(accountToSave)

        val savedAccount = accountService.findByUsername(accountToSave.username)!!

        val mageCharacter = CharacterRequest(
            name = "Mage",
            level = 10,
            unitArmor = 8,
            unitMagicResistance = 18,
            unitClass = UnitClass.CLASS_WIZARD,
            characterStat = unitStatService.findByComment("Mage")!!,
            characterSpells = mutableSetOf(
                spellService.findBySpellName("Atacar")!!,
                spellService.findBySpellName("Bola de Fuego")!!,
                spellService.findBySpellName("Descarga de Escarcha")!!
            ),
            unitModel = 0,
            account = savedAccount
        )

        val fighterCharacter = CharacterRequest(
            name = "Fighter",
            level = 10,
            unitArmor = 8,
            unitMagicResistance = 18,
            unitClass = UnitClass.CLASS_FIGHTER,
            characterStat = unitStatService.findByComment("Fighter")!!,
            characterSpells = mutableSetOf(
                spellService.findBySpellName("Atacar")!!,
                spellService.findBySpellName("Golpe contundente")!!,
            ),
            unitModel = 0,
            account = savedAccount
        )

        val paladinCharacter = CharacterRequest(
            name = "Paladin",
            level = 10,
            unitArmor = 16,
            unitMagicResistance = 15,
            unitClass = UnitClass.CLASS_PALADIN,
            characterStat = unitStatService.findByComment("Paladin")!!,
            characterSpells = mutableSetOf(
                spellService.findBySpellName("Atacar")!!,
                spellService.findBySpellName("Espada Sagrada")!!,
                spellService.findBySpellName("Sentencia")!!
            ),
            unitModel = 0,
            account = savedAccount
        )

        val rogueCharacter = CharacterRequest(
            name = "Rogue",
            level = 10,
            unitArmor = 8,
            unitMagicResistance = 18,
            unitClass = UnitClass.CLASS_ROGUE,
            characterStat = unitStatService.findByComment("Rogue")!!,
            characterSpells = mutableSetOf(
                spellService.findBySpellName("Ataque con daga")!!,
                spellService.findBySpellName("Puñalada")!!,
                spellService.findBySpellName("Arrojar daga")!!
            ),
            unitModel = 0,
            account = savedAccount
        )

        characterService.save(mageCharacter.toCharacter())
        characterService.save(fighterCharacter.toCharacter())
        characterService.save(paladinCharacter.toCharacter())
        characterService.save(rogueCharacter.toCharacter())

        return ResponseEntity.ok().build()
    }
}
