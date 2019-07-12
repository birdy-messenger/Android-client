package com.birdyteam.birdyandroidversion.domain.input

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 12.07.2019
 */

data class RegisterInput(
    val firstName: String,
    val email: String,
    val password: String,
    val confirmedPassword: String
)