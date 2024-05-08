package com.sercapcab.pathfinder.game.security

import java.security.SecureRandom
import java.security.spec.KeySpec
import java.util.HexFormat
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * Genera un salt aleatorio.
 * @return Salt generado como un array de bytes.
 */
fun generateRandomSalt(): ByteArray {
    val random = SecureRandom.getInstanceStrong()
    val salt = ByteArray(16)

    random.nextBytes(salt)

    return salt
}

private const val ALGORITHM = "PBKDF2WithHmacSHA512"
private const val ITERATIONS = 120_000
private const val KEY_LENGTH = 256
private val SECRET = System.getenv("SECRET")

/**
 * Calcula el hash de una contraseña utilizando PBKDF2 con SHA-512.
 * @param password Contraseña para la que se calculará el hash.
 * @return Hash de la contraseña como una cadena hexadecimal.
 */
fun hashPassword(password: String): Array<String> {
    val salt = generateRandomSalt().toHexString()
    val combinedSalt = "$salt$SECRET"

    val factory: SecretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
    val keySpec: KeySpec = PBEKeySpec(password.toCharArray(), combinedSalt.toByteArray(), ITERATIONS, KEY_LENGTH)
    val secretKey: SecretKey = factory.generateSecret(keySpec)
    val hash: ByteArray = secretKey.encoded

    return arrayOf(hash.toHexString(), combinedSalt)
}

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

fun checkPassword(inputPassword: String, salt: String, hashedPassword: String): Boolean {
    val factory: SecretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
    val keySpec: KeySpec = PBEKeySpec(inputPassword.toCharArray(), salt.toByteArray(), ITERATIONS, KEY_LENGTH)
    val secretKey: SecretKey = factory.generateSecret(keySpec)
    val hash: ByteArray = secretKey.encoded

    return hash.toHexString() == hashedPassword
}

/**
 * Convierte un array de bytes en su representación hexadecimal como una cadena de caracteres.
 *
 * @return La representación hexadecimal del array de bytes como una cadena de caracteres.
 */
private fun ByteArray.toHexString(): String =
    HexFormat.of().formatHex(this)
