package org.example.com.pathfinder.game.entity.item

/**
 * Enumeración que representa los tipos de objetos
 */
enum class ItemType(private val itemType: String) {
    ITEM_ARMOR("Armadura"),
    ITEM_WEAPON("Arma");
}

/**
 * Enumeración que representa los tipos de armaduras.
 */
enum class ArmorType(private val armorType: String) {
    LIGHT_ARMOR("Armadura ligera"),
    MEDIUM_ARMOR("Armadura media"),
    HEAVY_ARMOR("Armadura pesada"),
    SHIELD_ARMOR("Escudo");
}

enum class WeaponType(private val weaponType: String) {
    WEAPON_SWORD("Espada"),
    WEAPON_MACE("Maza"),
    WEAPON_BOW("Arco"),
    WEAPON_SCIMITAR("Cimitarra"),
    WEAPON_CROSSBOW("Ballesta"),
    WEAPON_STAFF("Bastón");
}