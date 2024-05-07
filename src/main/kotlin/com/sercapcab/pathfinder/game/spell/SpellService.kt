package com.sercapcab.pathfinder.game.spell

import java.util.*

interface SpellService {
    fun findAll(): List<Spell>
    fun findByUUID(spellUUID: UUID): Spell?
    fun save(spell: Spell)
    fun delete(spell: Spell)
    fun delete(spellUUID: UUID)
}
