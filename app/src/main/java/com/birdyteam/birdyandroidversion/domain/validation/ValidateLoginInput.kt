package com.birdyteam.birdyandroidversion.domain.validation

import com.birdyteam.birdyandroidversion.domain.input.LoginInput

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
class ValidateLoginInput {

    fun validate(input: LoginInput): ValidationResult {
        var passwordError: Errors? = null
        var emailError: Errors? = null
        if (!input.email.matches(Regex(InputPatterns.EMAIL_PATTERN)))
            emailError = Errors.NOT_MATCH_PATTERN
        if (!input.password.matches(Regex(InputPatterns.PASSWORD_PATTERN))) {
            passwordError = when (input.password.length) {
                in 0..InputPatterns.MIN_LEN -> {
                    Errors.TOO_SHORT
                }
                in InputPatterns.MIN_LEN + 1..InputPatterns.MAX_LEN -> {
                    Errors.NOT_MATCH_PATTERN
                }
                else -> {
                    Errors.TOO_LONG
                }
            }
        }
        if (passwordError == null && emailError == null)
            return ValidationSuccess
        return LoginValidationError().also {
            it.emailError = emailError
            it.passwordError = passwordError
        }
    }
}