package com.sercapcab.pathfinder.game.entity.character

import java.util.*

interface CharacterService {
    fun findAll(): List<Character>
    fun findByUUID(characterUUID: UUID): Character
    fun findByName(characterName: String): Character?
    fun save(character: Character)
    fun delete(character: Character)
    fun delete(characterUUID: UUID)
}
