package com.birdyteam.birdyandroidversion.domain.validation

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
object InputPatterns {
    const val MIN_LEN = 6
    const val MAX_LEN = 32
    const val EMAIL_PATTERN = "[\\w-]+@[\\w]+\\.[\\w]+"
    const val NAME_PATTERN = "[\\w]+"
    const val PASSWORD_PATTERN = "[\\w]{$MIN_LEN,$MAX_LEN}"
}