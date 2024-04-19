package org.example.com.pathfinder.game.spell

import com.pathfinder.Since
import com.pathfinder.game.spell.Spell
import com.pathfinder.game.spell.SpellInfo

@Since(version = "1.0")
enum class AuraType {
    PERMANENT_AURA,
    BUFF_AURA,
    DEBUFF_AURA
}

@Since(version = "1.0")
enum class RemoveAuraType {
    REMOVE_BY_TURNS,
    REMOVE_DISPELL
}

@Since(version = "1.0")
class SpellAura(
    override val spellInfo: SpellInfo,
    private val auraType: AuraType,
    private var turnsToRemove: Int
): Spell(spellInfo) {
    init {
        if (auraType == AuraType.PERMANENT_AURA)
            turnsToRemove = -1
    }

    fun getTurnsToRemove(): Int = this.turnsToRemove

    fun updateAuraTurns() {
        if (auraType != AuraType.PERMANENT_AURA)
            this.turnsToRemove--
    }
}