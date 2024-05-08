package com.sercapcab.pathfinder.game.entity.unit

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.entity.character.CharacterService
import com.sercapcab.pathfinder.game.entity.creature.CreatureService
import com.sercapcab.pathfinder.game.entity.unit.unitstat.UnitStat
import com.sercapcab.pathfinder.game.entity.unit.unitstat.UnitStatRequest
import com.sercapcab.pathfinder.game.entity.unit.unitstat.UnitStatService
import com.sercapcab.pathfinder.game.exceptions.unit.UnitHasDataException
import com.sercapcab.pathfinder.game.exceptions.unit.UnitStatHasDataException
import com.sercapcab.pathfinder.game.security.generateUUIDv5
import com.sercapcab.pathfinder.game.spell.Spell
import com.sercapcab.pathfinder.game.spell.SpellService
import jakarta.validation.Valid
import lombok.AllArgsConstructor
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.UUID

typealias type = MediaType
private const val json = type.APPLICATION_JSON_VALUE

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/unit")
@Since(version = "1.0")
class UnitRestController constructor(@Autowired private val unitService: UnitService,
                                     @Autowired private val unitStatService: UnitStatService,
                                     @Autowired private val characterService: CharacterService,
                                     @Autowired private val creatureService: CreatureService,
                                     @Autowired private val spellService: SpellService
){

    /**
     * Obtiene todas las unidades de la base de datos
     *
     * @return una [ResponseEntity] de [Unit]
     */
    @GetMapping(path = [""], produces = [json])
    @NotNull
    fun getAllUnits(): ResponseEntity<List<Unit>> {
        val units: List<Unit> = unitService.findAll().toList()

        return ResponseEntity.ok(units)
    }

    @GetMapping(path = ["/stat"], produces = [json])
    @NotNull
    fun getAllUnitStats(): ResponseEntity<List<UnitStat>> {
        val unitStats: List<UnitStat> = unitStatService.findAll().toList()

        return ResponseEntity.ok(unitStats)
    }

    @GetMapping(path = ["{id}/spells"], produces = [json])
    @NotNull
    fun getAllSpellsFromUnit(@PathVariable id: UUID): ResponseEntity<MutableSet<Spell>> {
        val unitSpells: MutableSet<Spell>? = unitService.findByUUID(id).unitSpells
        println(id)
        unitSpells?.forEach{println(it.spellUuid)}
        return ResponseEntity.ok(unitSpells)
    }

    @GetMapping(path = ["{id}"], produces = [json])
    @NotNull
    fun getSingleUnit(@PathVariable id: UUID): ResponseEntity<Unit> {
        val unit: Unit = unitService.findByUUID(id)

        return ResponseEntity.ok(unit)
    }

    @GetMapping(path = ["{id}/stat"], produces = [json])
    @NotNull
    fun getUnitStat(@PathVariable id: UUID): ResponseEntity<UnitStat> {
        val unitStat: UnitStat = unitStatService.findByUUID(id)

        return ResponseEntity.ok(unitStat)
    }

    @PostMapping(path = [""], produces = [json])
    @NotNull
    fun create(@Valid @RequestBody unit: UnitRequest): ResponseEntity<Unit> {
        unitService.save(unit.toUnit())

        /*unitCreated.unitSpells.forEach { spell ->
            spell.units.add(unitCreated)
            spellService.save(spell)
            println(spell)
        }*/

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("")
                    .buildAndExpand(unit).toUri()
            )
            .body(unit.toUnit())
    }

    @PostMapping(path = ["/stat"], produces = [json])
    @NotNull
    fun create(@RequestBody unitStat: UnitStatRequest): ResponseEntity<UnitStat> {
        val unitStatCreated = unitStatService.save(unitStat.toUnitStat())

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/stat")
                    .buildAndExpand(unitStatCreated).toUri()
            )
            .body(unitStatCreated)
    }

    @PutMapping(path = ["{id}"], produces = [json])
    fun updateUnit(@RequestBody unit: UnitRequest, @PathVariable id: UUID): ResponseEntity<Unit> {
        val foundUnit = unitService.findByUUID(id)

        foundUnit.name = unit.name
        foundUnit.level = unit.level
        foundUnit.unitArmor = unit.unitArmor
        foundUnit.unitMagicResistance = unit.unitMagicResistance
        foundUnit.unitStat = unit.unitStat
        foundUnit.unitSpells = unit.unitSpells
        foundUnit.comment = unit.comment

        unitService.save(foundUnit)

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(foundUnit).toUri()
            )
            .body(foundUnit)
    }

    @PutMapping(path = ["/stat/{id}"], produces = [json])
    fun updateUnitStats(@RequestBody unitStat: UnitStatRequest, @PathVariable id: UUID): ResponseEntity<UnitStat> {
        val foundUnitStat = unitStatService.findByUUID(id)

        foundUnitStat.strength = unitStat.strength
        foundUnitStat.dexterity = unitStat.dexterity
        foundUnitStat.constitution = unitStat.constitution
        foundUnitStat.intelligence = unitStat.intelligence
        foundUnitStat.wisdom = unitStat.wisdom
        foundUnitStat.charisma = unitStat.charisma
        foundUnitStat.comment = unitStat.comment

        val saveUnitStat = unitStatService.save(foundUnitStat)

        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/stat/{id}").buildAndExpand(saveUnitStat)
                    .toUri()
            )
            .body(saveUnitStat)
    }

    @DeleteMapping(path = ["{id}"], produces = [json])
    fun deleteUnit(@PathVariable id: UUID): ResponseEntity.HeadersBuilder<*> {
        val foundUnit = unitService.findByUUID(id)
        val isUnitBeingUsed = characterService.findAll().any { char -> char.unit.uuid == id}
                && creatureService.findAll().any { creature -> creature.unit.uuid == id}

        if (isUnitBeingUsed)
            throw UnitHasDataException(id)
        else
            unitService.delete(foundUnit)

        return ResponseEntity.noContent()
    }

    @DeleteMapping(path = ["stat/{id}"], produces = [json])
    fun deleteUnitStat(@PathVariable id: UUID): ResponseEntity.HeadersBuilder<*> {
        val foundUnitStats = unitStatService.findByUUID(id)
        val cantDelete = unitService.findAll().any { it.unitStat.uuid == id }

        if (cantDelete)
            throw UnitStatHasDataException(id)
        else
            unitStatService.delete(foundUnitStats)

        return ResponseEntity.noContent()
    }
}
