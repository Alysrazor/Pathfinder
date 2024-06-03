package com.sercapcab.pathfinder.game.entity.spell

import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SpellServiceImpl @Autowired constructor(private val spellRepository: SpellRepository): SpellService {
    override fun findAll(): List<Spell> {
        return spellRepository.findAll().toList()
    }

    override fun findByUUID(spellUUID: UUID): Spell {
        return spellRepository.findById(spellUUID)
            .orElseThrow { EntityNotFoundException(Spell::class.java, spellUUID) }
    }

    override fun findBySpellName(spellName: String): Spell? {
        return spellRepository.findAll().firstOrNull { it.spellName.contains(Regex(spellName))}
    }

    override fun save(spell: Spell): Spell {
        return spellRepository.save(spell)
    }

    override fun delete(spell: Spell) {
        spellRepository.delete(spell)
    }

    override fun delete(spellUUID: UUID) {
        spellRepository.deleteById(spellUUID)
    }
}
