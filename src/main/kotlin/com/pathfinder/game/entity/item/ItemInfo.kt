package org.example.com.pathfinder.game.entity.item

import com.pathfinder.Since
import com.pathfinder.game.spell.Spell

import java.util.UUID

/**
* Clase de datos que representa la información de un ítem.
*
* @property uuid El UUID único del ítem.
* @property name El nombre del ítem.
* @property itemType El tipo de ítem, puede ser un arma o una armadura.
* @property armorType El tipo de armadura, si el ítem es una armadura.
* @property weaponType El tipo de arma, si el ítem es un arma.
* @property armourClass El valor de la Clase de Armadura (AC) del ítem, si es una armadura.
* @property spells La lista de hechizos asociados al ítem, si los tiene.
*/
@Since(version = "1.0")
data class ItemInfo(
    val uuid: UUID,
    val name: String,
    val itemType: ItemType,
    val armorType: ArmorType?,
    val weaponType: WeaponType?,
    val armourClass: UInt?,
    val spells: List<Spell>?
)
