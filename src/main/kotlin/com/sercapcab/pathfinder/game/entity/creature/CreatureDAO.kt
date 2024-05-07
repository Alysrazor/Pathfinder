package com.sercapcab.pathfinder.game.entity.creature

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface CreatureDAO: CrudRepository<Creature, UUID>