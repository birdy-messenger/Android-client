package com.birdyteam.birdyandroidversion.domain.validation

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
sealed class ValidationResult

open class ValidationError(
    val emailErrorMessage: ValidationErrorMessage?,
    val passwordErrorMessage: ValidationErrorMessage?
) : ValidationResult()

class ExtendedValidationError (
    emailErrorMessage: ValidationErrorMessage? = null,
    passwordErrorMessage: ValidationErrorMessage? = null
) : ValidationError(emailErrorMessage, passwordErrorMessage) {

    var firstNameErrorMessage: ValidationErrorMessage? = null
    var confirmPasswordErrorMessage: ValidationErrorMessage? = null

    constructor(errors: Array<ValidationErrorMessage?>) : this(errors[0], errors[2]) {
        firstNameErrorMessage = errors[1]
        confirmPasswordErrorMessage = errors[3]
    }
}

data class ValidationErrorMessage(val errorMessage: ValidationErrorState)
object ValidationSuccess : ValidationResult()

enum class ValidationErrorState {
    TOO_SHORT,
    TOO_LONG,
    NOT_MATCH_PATTERN,
    NOT_MATCH_CONFIRM
}