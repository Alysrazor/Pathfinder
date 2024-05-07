package com.sercapcab.pathfinder.game.entity.character

import com.sercapcab.pathfinder.game.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CharacterServiceImpl @Autowired constructor(private val characterDAO: CharacterDAO): CharacterService {
    override fun findAll(): List<Character> {
        return characterDAO.findAll().toList()
    }

    override fun findByUUID(characterUUID: UUID): Character? {
        return characterDAO.findById(characterUUID)
            .orElseThrow { EntityNotFoundException(Character::class.java, characterUUID) }
    }

    override fun save(character: Character): Character {
        return characterDAO.save(character)
    }

    override fun delete(character: Character) {
        characterDAO.delete(character)
    }

    override fun delete(characterUUID: UUID) {
        characterDAO.deleteById(characterUUID)
    }
}