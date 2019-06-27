package com.birdyteam.birdyandroidversion.requests

class BirdyRequestUtils {
    companion object {
        const val HTTP = "https://"
        const val BIRDY = "birdytestapi.azurewebsites.net/"
        const val API = "api/"
    }
    class BirdyRequestMethods {
        companion object {
            const val AUTH = "auth?"
            const val USER_REG = "user.reg?"
        }
        class BirdyRequestAuthParams {
            companion object {
                const val EMAIL = "email"
                const val PASSWORD = "passwordHash"
                const val FIRST_NAME = "firstName"
            }
        }
    }
}