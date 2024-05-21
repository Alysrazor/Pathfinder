package com.sercapcab.pathfinder.game.exceptions.account

class AccountNotFoundException: Exception {
    constructor(): super()

    constructor(msg: String): super(msg)
}