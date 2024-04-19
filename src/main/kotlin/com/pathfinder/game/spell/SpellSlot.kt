package com.pathfinder.game.spell

import com.pathfinder.Since

@Deprecated(message = "Se usa ahora un recurso único.")
@Since(version = "1.0")
/**
 * Clase que representa un conjunto de espacios de hechizo.
 *
 * @property levelSlot El nivel del espacio de hechizo.
 * @property totalSlots Un array de bytes que representa los espacios de hechizo disponibles.
 * El valor `-1` indica un espacio vacío y el valor `1` indica un espacio disponible.
 * Por defecto, se inicializa con un espacio de hechizo disponible.
 */
class SpellSlot(private val levelSlot: UInt, private var totalSlots: ByteArray = ByteArray(4)) {
    init {
        totalSlots[0] = 1
        totalSlots.fill(-1, 1)
    }

    /**
     * Devuelve el nivel del espacio de hechizo.
     * @return El nivel del espacio de hechizo.
     */
    fun getSlotLevel(): UInt = this.levelSlot

    /**
     * Devuelve el número de espacios de hechizo disponibles estén o no gastados.
     * @return El número de espacios de hechizo disponibles.
     */
    fun getAvailableSlots(): Int = this.totalSlots.count { it.toInt() != -1 }

    /**
     * @return el número de espacios de hechizo que se pueden usar.
     */
    fun getUsableSlots(): Int = this.totalSlots.count { it.toInt() == 1 }

    /**
     * Recarga todos los espacios de hechizo, estableciéndolos como disponibles.
     */
    fun rechargeAllSlots() {
        this.totalSlots.fill(1)
    }

    /**
     * Añade un espacio de hechizo disponible si hay algún espacio vacío.
     */
    fun addAvailableSlot() {
        val index = totalSlots.indexOf(0)
        if (index != -1) {
            totalSlots[index] = 1
        }
    }

    override fun toString(): String {
        return String.format("Nivel Slot: %d - Cantidad de Slots: %d", this.levelSlot.toInt(), this.totalSlots.count { it.toInt() != -1 })
    }
}
