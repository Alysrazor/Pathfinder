package com.sercapcab.pathfinder.game.entity.creature

import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreatureServiceImpl(@Autowired private val creatureDAO: CreatureDAO): CreatureService {
    override fun findAll(): List<Creature> {
        return creatureDAO.findAll().toList()
    }

    override fun findByUUID(creatureUUID: UUID): Creature? {
        return creatureDAO.findById(creatureUUID)
            .orElseThrow() { EntityNotFoundException(Creature::class.java, creatureUUID)}
    }

    override fun save(creature: Creature): Creature? {
        return creatureDAO.save(creature)
    }

    override fun delete(creature: Creature) {
        creatureDAO.delete(creature)
    }

    override fun delete(creatureUUID: UUID) {
        creatureDAO.deleteById(creatureUUID)
    }
}