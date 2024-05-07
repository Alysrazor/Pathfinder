package com.sercapcab.pathfinder.game.entity.player

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.character.Character
import com.sercapcab.pathfinder.game.entity.character.CharacterService
import jakarta.validation.Valid
import lombok.AllArgsConstructor
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.UUID

typealias type = MediaType
private const val json = type.APPLICATION_JSON_VALUE

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

    @GetMapping(path = ["{id}/characters"], produces = [json])
    @NotNull
    fun getAllPlayerCharacters(@PathVariable id: UUID): ResponseEntity<Set<Character>> {
        val characters = playerService.findByUUID(id)?.characters

        return ResponseEntity.ok(characters)
    }

    @PostMapping(path = [""], produces = [json])
    @NotNull
    fun create(@Valid @RequestBody player: Player): ResponseEntity<Player> {
        playerService.save(player)

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(player).toUri()
            )
            .body(player)
    }
}