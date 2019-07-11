package com.birdyteam.birdyandroidversion.domain.validation

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
sealed class ValidationResult

data class ValidationError(
    val emailErrorMessage: ValidationErrorMessage?,
    val passwordErrorMessage: ValidationErrorMessage?
) : ValidationResult()

data class ValidationErrorMessage(val errorMessage: ValidationErrorState)
object ValidationSuccess : ValidationResult()

enum class ValidationErrorState {
    TOO_SHORT,
    TOO_LONG,
    NOT_MATCH_PATTERN
}