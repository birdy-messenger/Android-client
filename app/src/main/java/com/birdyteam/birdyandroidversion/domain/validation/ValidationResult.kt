package com.birdyteam.birdyandroidversion.domain.validation

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
sealed class ValidationResult
data class ValidationError(val errorMessage : String) : ValidationResult()
object ValidationSuccess : ValidationResult()