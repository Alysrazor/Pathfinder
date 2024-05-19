package com.sercapcab.pathfinder.game.security

import java.security.SecureRandom
import java.util.*


/**
 * Genera un secreto aleatorio de la longitud especificada.
 *
 * @param length Longitud del secreto generado.
 * @return El secreto generado como una cadena de caracteres.
 */
fun generateRandomSecret(length: Int): String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[{]}|;:',<.>/?"
    val random = SecureRandom()
    val randomChars = CharArray(length)
    repeat(length) { index ->
        randomChars[index] = charset[random.nextInt(charset.length)]
    }
    return String(randomChars)
}

/**
 * Convierte un array de bytes en su representación hexadecimal como una cadena de caracteres.
 *
 * @return La representación hexadecimal del array de bytes como una cadena de caracteres.
 */
private fun ByteArray.toHexString(): String =
    HexFormat.of().formatHex(this)
