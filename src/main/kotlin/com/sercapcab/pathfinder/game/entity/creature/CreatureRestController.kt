package com.sercapcab.pathfinder.game.entity.creature

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import com.sercapcab.pathfinder.game.entity.character.Character
import jakarta.validation.Valid
import lombok.AllArgsConstructor
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.UUID

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/creature")
@Since(version = "1.0")
class CreatureRestController constructor(@Autowired private val creatureService: CreatureService) {
    @GetMapping(path = [""], produces = [json])
    @NotNull
    fun getAllCreatures(): ResponseEntity<List<Creature>> {
        val creatures: List<Creature> = creatureService.findAll().toList()

        return if (creatures.isEmpty())
            ResponseEntity.noContent().build()
        else
            ResponseEntity.ok(creatures)
    }

    @GetMapping(path = ["{id}"], produces = [json])
    @NotNull
    fun getSingleCreature(@PathVariable id: UUID): ResponseEntity<Creature> {
        val creature: Creature = creatureService.findByUUID(id)

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(creature).toUri()
            )
            .body(creature)
    }

    @PostMapping(path = [""], produces = [json])
    @NotNull
    fun create(@Valid @RequestBody creature: CreatureRequest): ResponseEntity<Creature> {
        val creatureToSave = creature.toCreature()

        creatureService.save(creatureToSave)

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("")
                    .buildAndExpand(creatureToSave).toUri()
            )
            .body(creatureToSave)
    }

    @PutMapping(path = ["{id}"], produces = [json])
    @NotNull
    fun update(@Valid @RequestBody creature: CreatureRequest, @PathVariable id: UUID): ResponseEntity<Creature> {
        val foundCreature = creatureService.findByUUID(id)

        foundCreature.name = creature.name
        foundCreature.level = creature.level
        foundCreature.creatureStats = creature.creatureStats
        foundCreature.unitArmor = creature.unitArmor
        foundCreature.unitMagicResistance = creature.unitMagicResistance
        foundCreature.creatureSpells = creature.creatureSpells
        foundCreature.unitModel = creature.unitModel

        creatureService.save(foundCreature)

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(foundCreature).toUri()
            )
            .body(foundCreature)
    }
}