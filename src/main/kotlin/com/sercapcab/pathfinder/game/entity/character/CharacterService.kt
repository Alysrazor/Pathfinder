package com.sercapcab.pathfinder.game.entity.character

import java.util.*

interface CharacterService {
    fun findAll(): List<Character>
    fun findByUUID(characterUUID: UUID): Character?
    fun save(character: Character): Character?
    fun delete(character: Character)
    fun delete(characterUUID: UUID)
}
