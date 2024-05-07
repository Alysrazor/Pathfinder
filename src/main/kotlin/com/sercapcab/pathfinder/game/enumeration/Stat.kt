package com.sercapcab.pathfinder.game.enumeration

enum class Stat(private val statId: Int, val statName: String) {
    STAT_STRENGTH(1, "Fuerza"),
    STAT_DEXTERITY(2, "Destreza"),
    STAT_CONSTITUTION(3, "Constitución"),
    STAT_INTELLIGENCE(4, "Inteligencia"),
    STAT_WISDOM(5, "Sabiduría"),
    STAT_CHARISMA(6, "Carisma")
}