package com.birdyteam.birdyandroidversion.md5

import java.math.BigInteger
import java.security.MessageDigest

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 08.07.2019
 */

fun String.createMD5(): String {
    val md: MessageDigest = MessageDigest.getInstance("MD5")
    md.reset()
    md.update(this.toByteArray())
    val digest = md.digest()
    return BigInteger(1, digest)
        .toString(16)
        .padStart(32, '0')
}