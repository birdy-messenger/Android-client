package com.birdyteam.birdyandroidversion.domain.validation

import com.birdyteam.birdyandroidversion.domain.input.RegisterInput

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 12.07.2019
 */
class ValidateRegisterInput {
    fun validate(input: RegisterInput): ValidationResult {
        val errors = Array<Errors?>(3) { null }
        var passwordsMatch = true
        if (!input.email.matches(Regex(InputPatterns.EMAIL_PATTERN)))
            errors[0] = Errors.NOT_MATCH_PATTERN
        if (!input.userTag.matches(Regex(InputPatterns.NAME_PATTERN)))
            errors[1] = Errors.NOT_MATCH_PATTERN
        if (!input.password.matches(Regex(InputPatterns.PASSWORD_PATTERN))) {
            errors[2] = when (input.password.length) {
                in 0..InputPatterns.MIN_LEN -> Errors.TOO_SHORT
                in InputPatterns.MIN_LEN + 1..InputPatterns.MAX_LEN -> Errors.NOT_MATCH_PATTERN
                else -> Errors.TOO_LONG

            }
        }
        if (errors[2] == null && input.password != input.confirmedPassword)
            passwordsMatch = false
        if (errors.count { it != null } == 0 && passwordsMatch)
            return ValidationSuccess
        return RegistrationError().also {
            it.emailError = errors[0]
            it.userTagError = errors[1]
            it.passwordError = errors[2]
            it.passwordConfirmed = passwordsMatch
        }
    }
}