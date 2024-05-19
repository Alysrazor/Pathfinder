package com.sercapcab.pathfinder.game.entity.unitstat

import com.sercapcab.pathfinder.Since
import com.sercapcab.pathfinder.game.config.json
import lombok.AllArgsConstructor
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/unit-stat")
@Since(version = "1.0")
class UnitStatRestController(
    @Autowired private val unitStatService: UnitStatService
){

    @GetMapping(path = ["/"], produces = [json])
    @NotNull
    fun getAllUnitStats(): ResponseEntity<List<UnitStat>> {
        val unitStats: List<UnitStat> = unitStatService.findAll().toList()

        return if (unitStats.isEmpty())
            ResponseEntity.noContent().build()
        else
            ResponseEntity.ok(unitStats)
    }

    @GetMapping(path = ["{id}"], produces = [json])
    @NotNull
    fun getUnitStat(@PathVariable id: UUID): ResponseEntity<UnitStat> {
        val unitStat: UnitStat = unitStatService.findByUUID(id)

        return ResponseEntity.ok(unitStat)
    }

    @PostMapping(path = [""], produces = [json])
    @NotNull
    fun create(@RequestBody unitStat: UnitStatRequest): ResponseEntity<UnitStat> {
        val unitStatCreated = unitStatService.save(unitStat.toUnitStat())

        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/stat")
                .buildAndExpand(unitStatCreated).toUri()
        )
            .body(unitStatCreated)
    }

    @PutMapping(path = ["{id}"], produces = [json])
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

        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/stat/{id}").buildAndExpand(saveUnitStat)
                .toUri()
        )
            .body(saveUnitStat)
    }
}