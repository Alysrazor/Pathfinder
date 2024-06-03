package com.sercapcab.pathfinder.game.entity.spell

import java.util.*

interface SpellService {
    fun findAll(): List<Spell>
    fun findByUUID(spellUUID: UUID): Spell
    fun findBySpellName(spellName: String): Spell?
    fun save(spell: Spell): Spell
    fun delete(spell: Spell)
    fun delete(spellUUID: UUID)
}
