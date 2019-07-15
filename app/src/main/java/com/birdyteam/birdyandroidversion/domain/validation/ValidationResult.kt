package com.birdyteam.birdyandroidversion.domain.validation

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
sealed class ValidationResult

object ValidationSuccess : ValidationResult()

sealed class ValidationError : ValidationResult()

interface DefaultErrors {
    var emailError: Errors?
    var passwordError: Errors?
}

enum class Errors {
    TOO_SHORT,
    TOO_LONG,
    NOT_MATCH_PATTERN
}

class LoginValidationError : ValidationError(), DefaultErrors {
    override var emailError: Errors? = null
    override var passwordError: Errors? = null
}

class RegistrationError : ValidationError(), DefaultErrors {
    override var emailError: Errors? = null
    override var passwordError: Errors? = null
    var userTagError: Errors? = null
    var passwordConfirmed: Boolean = true
}