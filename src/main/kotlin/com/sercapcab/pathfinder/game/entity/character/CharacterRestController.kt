package com.sercapcab.pathfinder.game.entity.character

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import com.sercapcab.pathfinder.game.entity.spell.Spell
import com.sercapcab.pathfinder.game.entity.spell.SpellService
import com.sercapcab.pathfinder.game.enumeration.UnitClass
import jakarta.validation.Valid
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/api/v1/character")
@Since(version = "1.0")
class CharacterRestController(
    @Autowired private val characterService: CharacterService,
    @Autowired private val spellService: SpellService
) {
    @GetMapping(path = ["/"], produces = [json])
    @NotNull
    fun getAllCharacters(): ResponseEntity<List<Character>> {
        val characters: List<Character> = characterService.findAll().toList()

        return if (characters.isNotEmpty())
            ResponseEntity.ok(characters)
        else
            ResponseEntity.noContent().build()
    }

    @GetMapping(path = ["/id/{id}"], produces = [json])
    @NotNull
    fun getSingleCharacter(@PathVariable id: UUID): ResponseEntity<Character> {
        val character: Character = characterService.findByUUID(id)

        return ResponseEntity.ok(character)
    }

    @GetMapping(path = ["/name/{name}"], produces = [json])
    @NotNull
    fun getCharacterByName(@PathVariable name: String): ResponseEntity<Character?> {
        val character = characterService.findByName(name)

        return if (character != null)
            ResponseEntity.ok(character)
        else
            ResponseEntity.notFound().build()
    }

    @PostMapping(path = ["/"], produces = [json])
    @NotNull
    fun create(@Valid @RequestBody character: CharacterRequest): ResponseEntity<Character> {
        val attackSpell = spellService.findBySpellName("Atacar")!!
        val attackSpellDexterity = spellService.findBySpellName("Ataque con daga")!!

        val spells: MutableSet<Spell> = if(character.unitClass == UnitClass.CLASS_ROGUE)
                mutableSetOf(attackSpellDexterity)
        else
            mutableSetOf(attackSpell)

        spells.addAll(character.characterSpells)

        character.characterSpells = spells
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