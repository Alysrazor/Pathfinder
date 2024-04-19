package com.pathfinder.game.entity.unit


import com.pathfinder.Since
import com.pathfinder.Works
import jdk.jfr.Experimental
import kotlin.math.floor

/**
 * Enumerador de las estadísticas
 *
 * @property statId la id de la estadística
 * @property statName el nombre de la estadística
 */
@Experimental
@Since("1.0")
enum class Stat(private val statId: Int, val statName: String) {
    STAT_STRENGTH(1, "Fuerza"),
    STAT_DEXTERITY(2, "Destreza"),
    STAT_CONSTITUTION(3, "Constitución"),
    STAT_INTELLIGENCE(4, "Inteligencia"),
    STAT_WISDOM(5, "Sabiduría"),
    STAT_CHARISMA(6, "Carisma");

    companion object {
        /**
         * Calcula el modificador de habilidad basado en el valor de clase proporcionado.
         * El modificador de habilidad se utiliza para varias acciones en el juego, como tiradas de habilidades,
         * salvación, acciones de combate, etc.
         *
         * @param classValue El valor de clase que determina el modificador de habilidad.
         * @return El modificador de habilidad calculado.
         *
         * Ejemplos:
         * - getModifierChart(1) devuelve -5
         * - getModifierChart(2) devuelve -4
         * - getModifierChart(3) devuelve -4
         */
        @Works
        fun getModifierChart(classValue: Int): Int {
            require(classValue in 1..30) { "El valor de clase debe estar entre 1 y 30" }

            return floor((classValue - 10) / 2.0).toInt()
        }

        fun getModifierChartForStat(statValue: Int): Int {
            return floor((statValue - 10) / 2.0).toInt()
        }

        /**
         * Muestra la información de las estadísticas más legible.
         *
         * @return un [String] con la información
         */
        @Works
        fun statsInfo(stats: Map<Stat, Int>): String {
            var statsInfo = ""

            stats.map {
                statsInfo += String.format("%s: %d%n", it.key.statName, it.value)
            }

            return statsInfo
        }

    }
}

