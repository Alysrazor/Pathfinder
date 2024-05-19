package com.sercapcab.pathfinder.game.entity.spell

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface SpellDAO: CrudRepository<Spell, UUID>