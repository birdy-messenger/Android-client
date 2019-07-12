package com.birdyteam.birdyandroidversion.domain.validation

import com.birdyteam.birdyandroidversion.domain.input.RegisterInput

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 12.07.2019
 */
class ValidateRegisterInput {
    fun validate(input: RegisterInput): ValidationResult {
        val errors = Array<ValidationErrorMessage?>(4) { null }
        if (!input.email.matches(Regex(InputPatterns.EMAIL_PATTERN)))
            errors[0] = ValidationErrorMessage(ValidationErrorState.NOT_MATCH_PATTERN)
        if (!input.firstName.matches(Regex(InputPatterns.NAME_PATTERN)))
            errors[1] = ValidationErrorMessage(ValidationErrorState.NOT_MATCH_PATTERN)
        if (!input.password.matches(Regex(InputPatterns.PASSWORD_PATTERN))) {
            errors[2] = when (input.password.length) {
                in 0..InputPatterns.MIN_LEN -> {
                    ValidationErrorMessage(ValidationErrorState.TOO_SHORT)
                }
                in InputPatterns.MIN_LEN + 1..InputPatterns.MAX_LEN -> {
                    ValidationErrorMessage(ValidationErrorState.NOT_MATCH_PATTERN)
                }
                else -> {
                    ValidationErrorMessage(ValidationErrorState.TOO_LONG)
                }
            }
        }
        if (errors[2] == null && input.password != input.confirmedPassword)
            errors[3] = ValidationErrorMessage(ValidationErrorState.NOT_MATCH_CONFIRM)
        if (errors.count { it == null } == 0)
            return ValidationSuccess
        return ExtendedValidationError(errors)
    }
}