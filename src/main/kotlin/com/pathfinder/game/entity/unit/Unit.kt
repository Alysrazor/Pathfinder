package org.example.com.pathfinder.game.entity.unit

import com.pathfinder.Since
import com.pathfinder.Works

import com.pathfinder.game.entity.unit.Stat
import com.pathfinder.game.entity.unit.Stat.*
import com.pathfinder.game.entity.unit.UnitClass
import com.pathfinder.game.entity.unit.UnitClass.*
import com.pathfinder.game.spell.Spell
import java.util.UUID

private const val MAX_LEVEL = 10u

@Since(version = "1.0")
open class Unit(
    private val uuid: UUID = UUID.randomUUID(),
    private val name: String,
    private var level: UInt,
    private val unitClass: UnitClass,
    private var unitDefense: UnitDefense = UnitDefense(
      defenses = mapOf(
          DefenseType.Armor to 10,
          DefenseType.MagicResistance to 10
      )
    ),
    private var stats: Map<Stat, Int> = mapOf(
        STAT_STRENGTH to 10,
        STAT_DEXTERITY to 10,
        STAT_CONSTITUTION to 10,
        STAT_INTELLIGENCE to 10,
        STAT_WISDOM to 10,
        STAT_CHARISMA to 10
    ),
    private var spells: Set<Spell>
) {
    private var maxHealth: UInt = 0u
    private var currentHealth: UInt = 0u
    private var maxPower: UInt = 0u
    private var isDead: Boolean = false

    init {
        if (this.level > MAX_LEVEL)
            level = 10u

        updateHealthOnLevelOrConstitutionChange(this.level)
    }

    /**
     * Establece el valor máximo de salud de la unidad con el nuevo valor proporcionado.
     * El nuevo valor debe estar en el rango válido de 1 a 4294967295.
     *
     * @param newMaxHealth El nuevo valor máximo de salud que se va a establecer.
     * @throws IllegalArgumentException Si el nuevo valor no está dentro del rango válido (1 a 4294967295).
     */
    @Works
    fun setMaxHealth(newMaxHealth: UInt) {
        require(newMaxHealth in 1u..UInt.MAX_VALUE) { "Debe estar en el rango de 1 a 4294967295" }

        this.maxHealth = newMaxHealth
        this.currentHealth = newMaxHealth
    }

    /**
     * Establece las estadísticas de la unidad con las nuevas estadísticas proporcionadas.
     * Cada estadística debe estar en el rango válido de 1 a 30, de lo contrario se lanzará una excepción.
     *
     * @param newStats Las nuevas estadísticas en un [MutableMap]
     * @throws IllegalArgumentException Si alguna de las estadísticas está fuera del rango válido (1 a 30).
     */
    @Works
    fun setStats(newStats: Map<Stat, Int>) {
        newStats.map {
            require(it.value in 1..30) {"Las estadísticas tienen que estar entre 1 y 30"}
        }

        this.stats = newStats
    }

    /**
     * Actualiza las estadísticas de la unidad con los valores proporcionados en el mapa `updatedStats`
     * para aquellas estadísticas que coincidan en ambos mapas.
     *
     * Verifica que los valores en `updatedStats` estén dentro del rango válido de 1 a 30.
     *
     * @param updatedStats El mapa de estadísticas actualizadas que se utilizará para actualizar las estadísticas de la unidad.
     * @throws IllegalArgumentException Si alguna de las estadísticas en `updatedStats` está fuera del rango válido (1 a 30).
     */
    @Works
    fun updateStats(updatedStats: Map<Stat, Int>) {
        val mutableStats = this.stats.toMutableMap()

        updatedStats.forEach { (stat, value) ->
            require(value in 1..30) { "Las estadísticas deben estar entre 1 y 30" }

            if (mutableStats.containsKey(stat))
                mutableStats[stat] = value
        }

        this.stats = mutableStats

        if (updatedStats.containsKey(STAT_CONSTITUTION)) {
            updateHealthOnLevelOrConstitutionChange(this.level)
        }
    }

    /**
     * Actualiza los puntos de salud máximos y actuales de la unidad cuando cambia de nivel.
     * Calcula los puntos de salud máximos basados en el nivel y las estadísticas de la unidad.
     *
     * @param level El nuevo nivel de la unidad.
     */
    @Works
    private fun updateHealthOnLevelOrConstitutionChange(level: UInt) {
        val firstLevelHP: UInt = when (this.unitClass) {
            CLASS_BARBARIAN -> (12u + Stat.getModifierChart(this.stats.getValue(STAT_CONSTITUTION)).toUInt())
            CLASS_FIGHTER, CLASS_PALADIN, CLASS_RANGER -> (10u + Stat.getModifierChart(this.stats.getValue(STAT_CONSTITUTION)).toUInt())
            CLASS_BARD, CLASS_CLERIC, CLASS_DRUID,
            CLASS_MONK, CLASS_ROGUE, CLASS_WARLOCK ->
                (8u + Stat.getModifierChart(this.stats.getValue(STAT_CONSTITUTION)).toUInt())
            CLASS_SORCERER, CLASS_WIZARD -> (6u + Stat.getModifierChart(this.stats.getValue(STAT_CONSTITUTION)).toUInt())
        }

        val levelUpHealthPoints: UInt = when (this.unitClass) {
            CLASS_BARBARIAN -> (7u + Stat.getModifierChart(this.stats.getValue(STAT_CONSTITUTION)).toUInt())
            CLASS_FIGHTER, CLASS_PALADIN, CLASS_RANGER -> (6u + Stat.getModifierChart(this.stats.getValue(STAT_CONSTITUTION)).toUInt())
            CLASS_BARD, CLASS_CLERIC, CLASS_DRUID, CLASS_MONK, CLASS_ROGUE, CLASS_WARLOCK -> (5u + Stat.getModifierChart(this.stats.getValue(STAT_CONSTITUTION)).toUInt())
            CLASS_SORCERER, CLASS_WIZARD -> (4u + Stat.getModifierChart(this.stats.getValue(STAT_CONSTITUTION)).toUInt())
        }

        this.maxHealth = if (level.toInt() == 1) {
            firstLevelHP
        }
        else{
            firstLevelHP + (levelUpHealthPoints * (level - 1u))
        }

        this.currentHealth = maxHealth
    }
}