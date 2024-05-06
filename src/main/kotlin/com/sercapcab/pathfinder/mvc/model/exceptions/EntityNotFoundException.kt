package com.sercapcab.pathfinder.mvc.model.exceptions

import java.util.UUID

class EntityNotFoundException : RuntimeException {

    constructor() : super()

    constructor(entityClass: Class<*>, id: Long) : super("${entityClass.simpleName} not found: $id")

    constructor(entityClass: Class<*>, uuid: UUID) : super("${entityClass.simpleName} not found: $uuid")

    constructor(entityClass: Class<*>, name: String) : super("${entityClass.simpleName} not found: $name")
}
