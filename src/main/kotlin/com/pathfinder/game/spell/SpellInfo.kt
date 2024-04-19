package com.pathfinder.game.spell

import com.pathfinder.Since
import jdk.jfr.Experimental

import java.util.UUID

/**
 * Enumeración que representa las distintas escuelas de magia en el juego.
 *
 * Cada escuela de magia tiene un nombre asociado que describe su tipo de daño o efecto.
 *
 * @property schoolName El nombre de la escuela de magia.
 */
@Experimental
@Since(version = "1.0")
enum class SpellSchool(private val schoolName: String) {
    SCHOOL_ACID("Ácido"),
    SCHOOL_BLUDGEONING("Contundente"),
    SCHOOL_COLD("Frío"),
    SCHOOL_FIRE("Fuego"),
    SCHOOL_FORCE("Fuerza"),
    SCHOOL_LIGHTNING("Rayo"),
    SCHOOL_NECROTIC("Necrótico"),
    SCHOOL_PIERCING("Perforante"),
    SCHOOL_PHYSIC("Físico"),
    SCHOOL_POISON("Veneno"),
    SCHOOL_RADIANT("Radiante"),
    SCHOOL_SLASHING("Cortante"),
    SCHOOL_THUNDER("Trueno")
}

/**
 * Clase de datos que representa la información de un hechizo en el juego.
 *
 * @property uuid El identificador único del hechizo, generado automáticamente si no se proporciona.
 * @property name El nombre del hechizo.
 * @property minSpellSlotLevel El nivel mínimo de la ranura de hechizo requerido para lanzar el hechizo.
 * @property spellSchool La escuela de magia a la que pertenece el hechizo.
 */
@Since(version = "1.0")
data class SpellInfo(
    val uuid: UUID = UUID.randomUUID(),
    val name: String,
    val minSpellSlotLevel: UInt,
    val spellSchool: SpellSchool
)