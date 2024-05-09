package com.sercapcab.pathfinder.game.exceptions

import java.util.*

class EntityAlreadyExistsException : RuntimeException {

    constructor() : super()

    constructor(entityClass: Class<*>, id: Long) : super("${entityClass.simpleName} with id: $id already exists.")

    constructor(entityClass: Class<*>, uuid: UUID) : super("${entityClass.simpleName} with uuid: $uuid already exists.")

    constructor(entityClass: Class<*>, name: String) : super("${entityClass.simpleName} with name: $name already exists.")
}
