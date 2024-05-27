package com.sercapcab.pathfinder.game.entity.character

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface CharacterRepository: CrudRepository<Character, UUID>