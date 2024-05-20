package com.sercapcab.pathfinder.game.entity.spell

import com.sercapcab.pathfinder.game.security.generateUUIDv5
import lombok.AllArgsConstructor
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

typealias type = MediaType

private const val json = type.APPLICATION_JSON_VALUE

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/spell")
class SpellRestController constructor(
    @Autowired private val spellService: SpellService
) {

    @GetMapping(path = ["/"])
    @NotNull
    fun getAllSpells(): ResponseEntity<List<Spell>> {
        val spells: List<Spell> = spellService.findAll()

        return if (spells.isEmpty())
            ResponseEntity.noContent().build()
        else
            return ResponseEntity.ok(spells)
    }

    @GetMapping(path = ["{id}"])
    @NotNull
    fun getSpellById(@PathVariable spellUUID: UUID): ResponseEntity<Spell> {
        val spell: Spell = spellService.findByUUID(spellUUID)

        return ResponseEntity.ok(spell)
    }

    @PostMapping(path = ["/"], produces = [json])
    @NotNull
    fun create(@RequestBody spell: SpellRequest): ResponseEntity<Spell> {
        val spellCreated: Spell = spellService.save(spell.toSpell())

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("").buildAndExpand(spellCreated).toUri()
            ).body(spellCreated)
    }

    @PutMapping(path = ["{id}"], produces = [json])
    @NotNull
    fun update(@PathVariable id: UUID, @RequestBody spellToUpdate: SpellRequest): ResponseEntity<Spell> {
        val foundSpell = spellService.findByUUID(id)

        foundSpell.spellName = spellToUpdate.spellName
        foundSpell.spellDescription = spellToUpdate.spellDescription
        foundSpell.spellSchool = spellToUpdate.spellSchool
        foundSpell.baseDamage = spellToUpdate.baseDamage
        foundSpell.statModifier = spellToUpdate.statModifier
        foundSpell.statMultiplier = spellToUpdate.statMultiplier

        val saveSpell = spellService.save(foundSpell)

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(saveSpell).toUri()
            ).build()
    }

    @DeleteMapping(path = ["{id}"], produces = [json])
    @NotNull
    fun delete(@PathVariable id: UUID): ResponseEntity.HeadersBuilder<*> {
        val foundSpell = spellService.findByUUID(id)

        spellService.delete(foundSpell)

        return ResponseEntity.noContent()
    }
}
