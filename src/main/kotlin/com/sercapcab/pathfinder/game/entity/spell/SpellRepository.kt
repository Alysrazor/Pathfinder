package com.sercapcab.pathfinder.game.entity.spell

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface SpellRepository: CrudRepository<Spell, UUID> {
    fun findBySpellName(spellName: String): Spell?
}