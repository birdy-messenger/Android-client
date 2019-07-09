package com.birdyteam.birdyandroidversion.domain.validation

import com.birdyteam.birdyandroidversion.data.network.api.app.input.LoginInput

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
class ValidateLoginInput {
    fun validate(input: LoginInput): ValidationResult {
        if(!input.email.matches(Regex(InputPatterns.EMAIL_PATTERN)))
            return ValidationResult.EMAIL_NOT_MATCH
        if(!input.password.matches(Regex(InputPatterns.PASSWORD_PATTERN))) {
            return when (input.password.length) {
                in 0..5 -> {
                   ValidationResult.TOO_SHORT_PASSWORD
                }
                else -> {
                    ValidationResult.TOO_LONG_PASSWORD
                }
            }
        }
        return ValidationResult.SUCCESS
    }
}