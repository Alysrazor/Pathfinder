package com.sercapcab.pathfinder.game.exceptions.account

class AccountAlreadyExistsException: Exception {
    constructor(): super()

    constructor(msg: String): super(msg)
}