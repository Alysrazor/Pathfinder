package com.sercapcab.pathfinder.mvc.model.dao

import com.sercapcab.pathfinder.mvc.model.entity.Spell
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface SpellDAO: CrudRepository<Spell, UUID>