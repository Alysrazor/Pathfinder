package com.sercapcab.pathfinder.game.entity.character

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface CharacterDAO: CrudRepository<Character, UUID>