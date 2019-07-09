package com.birdyteam.birdyandroidversion.domain.validation

import android.content.res.Resources
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.data.network.api.app.input.LoginInput

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
class ValidateLoginInput(
    resources: Resources
) {
    private val errors = resources.getStringArray(R.array.validation_errors)

    fun validate(input: LoginInput): ValidationResult {
        if (!input.email.matches(Regex(InputPatterns.EMAIL_PATTERN)))
            return ValidationError(errors[0])
        if (!input.password.matches(Regex(InputPatterns.PASSWORD_PATTERN))) {
            return when (input.password.length) {
                in 0..5 -> {
                    ValidationError(errors[1])
                }
                in 6..32 -> {
                    ValidationError(errors[2])
                }
                else -> {
                    ValidationError(errors[3])
                }
            }
        }
        return ValidationSuccess
    }
}