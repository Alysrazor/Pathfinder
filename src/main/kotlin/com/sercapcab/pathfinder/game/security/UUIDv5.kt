package com.sercapcab.pathfinder.game.security

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or

/**
 * Genera un UUID versión 5 basado en el algoritmo de hash SHA-1 y el espacio de nombres proporcionados.
 *
 * @param namespace El UUID que representa el espacio de nombres para el UUID generado.
 * @return El UUID versión 5 generado.
 */
fun generateUUIDv5(namespace: UUID): UUID {
    val message = namespace.toString() + UUID.randomUUID()
    val bytes = message.toByteArray(StandardCharsets.UTF_8)

    val sha1 = MessageDigest.getInstance("SHA-1")
    val hashBytes = sha1.digest(bytes)

    hashBytes[6] = (hashBytes[6] and 0x0f.toByte() or 0x50.toByte())
    hashBytes[7] = (hashBytes[7] and 0x3f.toByte() or 0x80.toByte())

    val longValue = UUID.nameUUIDFromBytes(hashBytes)

    return UUID(longValue.mostSignificantBits, longValue.leastSignificantBits)
}