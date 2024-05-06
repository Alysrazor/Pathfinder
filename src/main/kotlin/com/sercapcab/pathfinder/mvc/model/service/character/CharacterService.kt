package com.sercapcab.pathfinder.mvc.model.service.character

import com.sercapcab.pathfinder.mvc.model.entity.Character
import java.util.*

interface CharacterService {
    fun findAll(): List<Character>
    fun findByUUID(characterUUID: UUID): Character?
    fun save(character: Character)
    fun delete(character: Character)
    fun delete(characterUUID: UUID)
}
