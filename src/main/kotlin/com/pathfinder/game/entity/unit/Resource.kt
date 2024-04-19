package com.pathfinder.game.entity.unit

import com.pathfinder.Since

import com.pathfinder.game.entity.unit.UnitClass.*
import com.pathfinder.game.spell.SpellSlot
import jdk.jfr.Experimental

@Experimental
@Since(version = "1.0")
enum class ResourceType(val resourceName: String) {
    // Common
    ACTION("Acción"),
    BONUS_ACTION("Acción adicional"),
    REACTION("Reacción"),

    // Spell Slots
    SPELL_SLOT("Espacios de conjuro"),
    WARLOCK_SPELL_SLOT("Espacios de conjuro de brujo"),

    // Class Resources
    ARCANE_RECOVERY_CHARGES("Cargas de recuperación arcana"),
    BARDIC_INSPIRATION_CHARGES("Cargas de inspiración bárdica"),
    FUNGAL_INFESTATION_CHARGES("Cargas de infestación fúngica"),
    LAY_OH_HANDS_CHARGES("Cargas de imposición de manos"),
    NATURAL_RECOVERY_CHARGES("Cargas de recuperación natural"),
    RAGE_CHARGES("Cargas de furia"),
    SORCERY_POINTS("Puntos de hechicería"),
    WAR_PRIEST_CHARGES("Cargas del sacerdote de la guerra"),
    CHANNEL_DIVINITY_CHARGES("Cargas de canalización divina"),
    CHANNEL_OATH_CHARGES("Cargas de canalización de juramento"),
    SUPERIORITY_DICE("Dados de supremacía"),
    KI_POINTS("Puntos de ki."),
    TIDES_OF_CHAOS_CHARGES("Cargas de mareas del caos"),
    WILD_SHAPE_CHARGES("Cargas de forma animal")
}

/**
 * Clase que representa un recurso en el juego, como cargas de habilidades o ranuras de hechizos.
 *
 * @property resourceType El tipo de recurso.
 * @property chargesOrSlots La cantidad de cargas o las ranuras de hechizos asociadas al recurso.
 */
@Experimental
@Since(version = "1.0")
class Resource(private val resourceType: ResourceType, private var chargesOrSlots: Any) {
    companion object {
        /**
         * Obtiene los recursos por defecto de una unidad
         *
         * @return un [Set] con los recursos por defecto
         */
        fun getDefaultResources(): Set<Resource> =
            setOf(Resource(ResourceType.ACTION, 1),
                Resource(ResourceType.BONUS_ACTION, 1),
                Resource(ResourceType.REACTION, 1))
        /**
         * Devuelve un conjunto de recursos inicial para una clase específica.
         *
         * @param unitClassType El tipo de clase para el que se obtienen los recursos iniciales.
         * @return Un conjunto de recursos inicial para la clase especificada.
         */
        fun getInititalResourcesForClass(unitClassType: UnitClass): Set<Resource> {
            val initialResources = getDefaultResources()

            return when (unitClassType) {
                CLASS_BARBARIAN -> initialResources + setOf(Resource(ResourceType.RAGE_CHARGES, 2))
                CLASS_BARD -> initialResources + setOf(
                    Resource(ResourceType.BARDIC_INSPIRATION_CHARGES, 3),
                    Resource(ResourceType.SPELL_SLOT, setOf(SpellSlot(1u)))
                )
                CLASS_CLERIC, CLASS_DRUID, CLASS_RANGER, CLASS_ROGUE, CLASS_SORCERER  ->
                    initialResources + setOf(Resource(ResourceType.SPELL_SLOT, setOf(SpellSlot(1u))))
                CLASS_MONK -> initialResources + setOf(Resource(ResourceType.KI_POINTS, 2))
                CLASS_PALADIN -> initialResources + setOf(
                    Resource(ResourceType.SPELL_SLOT, setOf(
                        SpellSlot(1u)
                    )),
                    Resource(ResourceType.LAY_OH_HANDS_CHARGES, 3),
                    Resource(ResourceType.CHANNEL_OATH_CHARGES, 1)
                )
                CLASS_WARLOCK -> initialResources + setOf(Resource(ResourceType.WARLOCK_SPELL_SLOT, SpellSlot(1u, totalSlots = ByteArray(3))))
                CLASS_WIZARD -> initialResources + setOf(
                    Resource(ResourceType.ARCANE_RECOVERY_CHARGES, 1),
                    Resource(ResourceType.SPELL_SLOT, setOf(SpellSlot(1u)))
                )
                else -> getDefaultResources()
            }
        }

        /**
         * Genera una cadena que contiene información sobre los recursos proporcionados.
         *
         * @param resources El conjunto de recursos del que se desea generar información.
         * @return Una cadena que contiene información sobre los recursos proporcionados.
         */

        fun resourcesInfo(resources: Set<Resource>): String {
            var resourcesInfo = ""

            resources.forEach { resource ->
                val spellSlotInfo = if (resource.chargesOrSlots is Set<*>) {
                    val spellSlotSet = (resource.chargesOrSlots as? Set<*>)
                        ?.filterIsInstance<SpellSlot>()
                        ?.joinToString(", ") { it.toString() } ?: ""
                    spellSlotSet
                } else {
                    resource.chargesOrSlots.toString()
                }

                resourcesInfo += "${resource.resourceType} - $spellSlotInfo\n"
            }

            return resourcesInfo
        }
    }
}
