package com.sercapcab.pathfinder.game.entity.character

import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CharacterServiceImpl @Autowired constructor(private val characterRepository: CharacterRepository): CharacterService {
    override fun findAll(): List<Character> {
        return characterRepository.findAll().toList()
    }

    override fun findByUUID(characterUUID: UUID): Character {
        return characterRepository.findById(characterUUID)
            .orElseThrow { EntityNotFoundException(Character::class.java, characterUUID) }
    }

    override fun findByName(characterName: String): Character? {
        return characterRepository.findByName(characterName)
    }

    override fun save(character: Character){
        characterRepository.save(character)
    }

    override fun delete(character: Character) {
        characterRepository.delete(character)
    }

    override fun delete(characterUUID: UUID) {
        characterRepository.deleteById(characterUUID)
    }
}