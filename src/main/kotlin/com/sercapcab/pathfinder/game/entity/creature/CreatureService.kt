package com.sercapcab.pathfinder.game.entity.creature

import java.util.UUID

interface CreatureService {
    fun findAll(): List<Creature>
    fun findByUUID(creatureUUID: UUID): Creature?
    fun save(creature: Creature): Creature?
    fun delete(creature: Creature)
    fun delete(creatureUUID: UUID)
}