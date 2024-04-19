package org.example.com.pathfinder.game.entity.unit

import com.pathfinder.Since
import jdk.jfr.Experimental

@Experimental
@Since(version = "1.0")
enum class DefenseType {
    Armor, MagicResistance
}

@Experimental
@Since(version = "1.0")
class UnitDefense(
    var defenses: Map<DefenseType, Int> = mapOf(
        DefenseType.Armor to 0,
        DefenseType.MagicResistance to 0
    )
) {
    fun calculateDamageReduction(armorAmount: Int): Double {
        return 100.0 / (100.0 + armorAmount)
    }
}