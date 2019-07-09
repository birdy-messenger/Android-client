package com.birdyteam.birdyandroidversion.domain.validation

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
enum class ValidationResult {
    TOO_LONG_PASSWORD,
    TOO_SHORT_PASSWORD,
    EMAIL_NOT_MATCH,
    PASSWORD_NOT_MATCH,
    SUCCESS
}