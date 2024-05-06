package com.sercapcab.pathfinder.mvc.model.service.character

import com.sercapcab.pathfinder.mvc.model.dao.CharacterDAO
import com.sercapcab.pathfinder.mvc.model.entity.Character
import com.sercapcab.pathfinder.mvc.model.exceptions.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class CharacterServiceImpl @Autowired constructor(private val characterDAO: CharacterDAO): CharacterService {
    override fun findAll(): List<Character> {
        return characterDAO.findAll().toList()
    }

    override fun findByUUID(characterUUID: UUID): Character? {
        return characterDAO.findById(characterUUID)
            .orElseThrow { EntityNotFoundException(Character::class.java, characterUUID) }
    }

    override fun save(character: Character) {
        characterDAO.save(character)
    }

    override fun delete(character: Character) {
        characterDAO.delete(character)
    }

    override fun delete(characterUUID: UUID) {
        characterDAO.deleteById(characterUUID)
    }
}