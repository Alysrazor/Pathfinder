package com.sercapcab.pathfinder.game.enumeration

enum class UnitClass(val id: Int, private val className: String) {
    CLASS_BARBARIAN(1, "Bárbaro"),
    CLASS_BARD(2, "Bardo"),
    CLASS_CLERIC(3, "Clérigo"),
    CLASS_DRUID(4, "Druida"),
    CLASS_FIGHTER(5, "Luchador"),
    CLASS_MONK(6, "Monje"),
    CLASS_PALADIN(7, "Paladín"),
    CLASS_RANGER(8, "Explorador"),
    CLASS_ROGUE(9, "Pícaro"),
    CLASS_SORCERER(10, "Hechicero"),
    CLASS_WARLOCK(11, "Brujo"),
    CLASS_WIZARD(12, "Mago")
}