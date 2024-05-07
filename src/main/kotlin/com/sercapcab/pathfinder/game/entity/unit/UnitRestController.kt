package com.sercapcab.pathfinder.game.entity.unit

import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import com.sercapcab.pathfinder.game.spell.SpellService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@CrossOrigin(origins= ["*"])
@RestController
@RequestMapping("/api/v1/unit")
class UnitRestController constructor(@Autowired private val unitService: UnitService,
                                     @Autowired private val unitStatService: UnitStatService,
                                     @Autowired private val spellService: SpellService
){

    /**
     * Obtiene todas las unidades de la base de datos
     *
     * @return una [ResponseEntity] de [Unit]
     */
    @GetMapping(path = ["", "/"])
    @ResponseStatus(HttpStatus.OK)
    fun getAllUnits(): ResponseEntity<*> {
        val units: List<Unit> = unitService.findAll().toList()

        return if (units.isEmpty())
            ResponseEntity<List<Unit>>(units, HttpStatus.OK)
        else
            ResponseEntity.noContent().build<Unit>()
    }

    @GetMapping(path = ["/stats", "/stats/"])
    @ResponseStatus(HttpStatus.OK)
    fun getAllUnitStats(): ResponseEntity<*> {
        val unitStats: List<UnitStat> = unitStatService.findAll().toList()

        return if (unitStats.isEmpty())
            ResponseEntity<List<UnitStat>>(unitStats, HttpStatus.OK)
        else
            ResponseEntity.noContent().build<UnitStat>()
    }

    @GetMapping(path = ["{id}", "{id}/"])
    @ResponseStatus(HttpStatus.OK)
    fun getSingleUnit(@PathVariable id: UUID): Unit? {
        return unitService.findByUUID(id)
    }

    @GetMapping(path = ["{id}/stat", "{id}/stat/"])
    @ResponseStatus(HttpStatus.OK)
    fun getUnitStat(@PathVariable id: UUID): UnitStat? {
        return unitService.findByUUID(id)?.unitStat
    }

    @PostMapping(path = ["", "/"])
    @ResponseStatus(HttpStatus.CREATED)
    fun addUnit(@RequestBody unit: Unit): Unit {
        unitService.save(unit)

        return unit
    }

    @PostMapping(path = ["stat", "stat/"])
    @ResponseStatus(HttpStatus.CREATED)
    fun addUnitStats(@RequestBody unitStat: UnitStat): UnitStat {
        unitStatService.save(unitStat)

        return unitStat
    }

    @PutMapping(path = ["{id}", "/{id} "])
    @ResponseStatus(HttpStatus.CREATED)
    fun updateUnit(@RequestBody unit: Unit, @PathVariable id: UUID): Unit {
        val foundUnit = unitService.findByUUID(id) ?: throw EntityNotFoundException(Unit::class.java, unit.uuid)

        foundUnit.name = unit.name
        foundUnit.level = unit.level
        foundUnit.unitArmor = unit.unitArmor
        foundUnit.unitMagicResistance = unit.unitMagicResistance
        foundUnit.unitStat = unit.unitStat
        foundUnit.unitSpells = unit.unitSpells
        foundUnit.comment = unit.comment

        return foundUnit
    }

    @PutMapping(path = ["/stats/{id}", "/stats/{id}/"])
    @ResponseStatus(HttpStatus.CREATED)
    fun updateUnitStats(@RequestBody unitStat: UnitStat, @PathVariable id: UUID): UnitStat {
        val foundUnitStat = unitStatService.findByUUID(id) ?: throw EntityNotFoundException(UnitStat::class.java, unitStat.uuid)

        foundUnitStat.strength = unitStat.strength
        foundUnitStat.dexterity = unitStat.dexterity
        foundUnitStat.constitution = unitStat.constitution
        foundUnitStat.intelligence = unitStat.intelligence
        foundUnitStat.wisdom = unitStat.wisdom
        foundUnitStat.charisma = unitStat.charisma
        foundUnitStat.comment = unitStat.comment

        return foundUnitStat
    }

    @DeleteMapping(path = ["{id}", "{id}/"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUnit(@PathVariable id: UUID) {
        TODO("Implementar Spell para poder borrar la unidad")
    }
}
