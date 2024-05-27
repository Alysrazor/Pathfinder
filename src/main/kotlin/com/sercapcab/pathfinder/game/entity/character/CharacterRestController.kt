package com.sercapcab.pathfinder.game.entity.character

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import jakarta.validation.Valid
import lombok.AllArgsConstructor
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/api/v1/character")
@Since(version = "1.0")
class CharacterRestController constructor(@Autowired private val characterService: CharacterService) {
    @GetMapping(path = ["/"], produces = [json])
    @NotNull
    fun getAllCharacters(): ResponseEntity<List<Character>> {
        val characters: List<Character> = characterService.findAll().toList()

        return ResponseEntity.ok(characters)
    }

    @GetMapping(path = ["{id}"], produces = [json])
    @NotNull
    fun getSingleCharacter(@PathVariable id: UUID): ResponseEntity<Character> {
        val character: Character = characterService.findByUUID(id)

        return ResponseEntity.ok(character)
    }

    @PostMapping(path = ["/"], produces = [json])
    @NotNull
    fun create(@Valid @RequestBody character: CharacterRequest): ResponseEntity<Character> {
        characterService.save(character.toCharacter())

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("")
                    .buildAndExpand(character).toUri()
            )
            .body(character.toCharacter())
    }

    @DeleteMapping(path = ["{id}"], produces = [json])
    fun delete(@PathVariable id: UUID): ResponseEntity.HeadersBuilder<*> {
        val foundCharacter = characterService.findByUUID(id)

        characterService.delete(foundCharacter)

        return ResponseEntity.noContent()
    }
}