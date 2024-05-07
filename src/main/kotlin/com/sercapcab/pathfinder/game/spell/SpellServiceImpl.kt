package com.sercapcab.pathfinder.game.spell

import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SpellServiceImpl @Autowired constructor(private val spellDAO: SpellDAO): SpellService {
    override fun findAll(): List<Spell> {
        return spellDAO.findAll().toList()
    }

    override fun findByUUID(spellUUID: UUID): Spell? {
        return spellDAO.findById(spellUUID)
            .orElseThrow { EntityNotFoundException(Spell::class.java, spellUUID) }
    }

    override fun save(spell: Spell) {
        spellDAO.save(spell)
    }

    override fun delete(spell: Spell) {
        spellDAO.delete(spell)
    }

    override fun delete(spellUUID: UUID) {
        spellDAO.deleteById(spellUUID)
    }
}
