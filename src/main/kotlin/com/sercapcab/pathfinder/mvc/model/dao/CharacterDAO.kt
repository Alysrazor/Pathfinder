package com.sercapcab.pathfinder.mvc.model.dao

import com.sercapcab.pathfinder.mvc.model.entity.Character
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface CharacterDAO: CrudRepository<Character, UUID>