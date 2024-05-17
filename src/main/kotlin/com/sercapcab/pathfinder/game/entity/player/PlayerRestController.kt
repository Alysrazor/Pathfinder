package com.sercapcab.pathfinder.game.entity.player

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import com.sercapcab.pathfinder.game.entity.character.Character
import com.sercapcab.pathfinder.game.entity.character.CharacterService
import com.sercapcab.pathfinder.game.exceptions.EntityAlreadyExistsException
import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import com.sercapcab.pathfinder.game.security.checkPassword
import jakarta.validation.Valid
import lombok.AllArgsConstructor
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.UUID

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/player")
@Since(version = "1.0")
class PlayerRestController constructor(
    @Autowired private val playerService: PlayerService,
    @Autowired private val characterService: CharacterService) {

    @GetMapping(path = [""], produces = [json])
    @NotNull
    fun getAllPlayers(): ResponseEntity<List<Player>> {
        val players = playerService.findAll().toList()

        return ResponseEntity.ok(players)
    }

    @GetMapping(path = ["{username}"], produces = [json])
    @NotNull
    fun getSinglePlayer(@PathVariable username: String): ResponseEntity<Player> {
        val player = playerService.findByPlayerName(username)
        
        return if (player.isPresent)
            ResponseEntity.ok(player.get())
        else
            throw EntityNotFoundException(Player::class.java, username)
    }

    @GetMapping(path = ["{id}/characters"], produces = [json])
    @NotNull
    fun getAllPlayerCharacters(@PathVariable id: UUID): ResponseEntity<Set<Character>> {
        val characters = playerService.findByUUID(id)?.characters

        return ResponseEntity.ok(characters)
    }

    @PostMapping(path = [""], produces = [json])
    @NotNull
    fun create(@Valid @RequestBody player: PlayerRequest): ResponseEntity<Player> {
        val playerToSave = player.toPlayer()

        if (playerService.findByPlayerName(playerToSave.playerName).isPresent)
            throw EntityAlreadyExistsException(Player::class.java, playerToSave.playerName)

        playerService.save(playerToSave)

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("")
                    .buildAndExpand(player).toUri()
            )
            .body(playerToSave)
    }

    @PutMapping(path = ["{id}"], produces = [json])
    @NotNull
    fun update(@Valid @RequestBody player: PlayerRequest, @PathVariable id: UUID): ResponseEntity<Player> {
        val foundPlayer = playerService.findByUUID(id)

        foundPlayer?.playerName = player.playerName
        foundPlayer?.password = player.password
        foundPlayer?.androidVersion = player.androidVersion

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(player).toUri()
            )
            .body(player.toPlayer())
    }

    @DeleteMapping(path = ["{id}"], produces = [json])
    @NotNull
    fun deletePlayer(@PathVariable id: UUID): ResponseEntity.HeadersBuilder<*> {
        playerService.delete(id)

        return ResponseEntity.noContent()
    }
}