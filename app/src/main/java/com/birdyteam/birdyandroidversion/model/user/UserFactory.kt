package com.birdyteam.birdyandroidversion.model.user

class UserFactory private constructor(){

    companion object {
        var currentUser : User? = null
        fun createUser(access : Long, id : Int) {
            currentUser = User()
            currentUser?.accessToken = access
            currentUser?.userId = id
        }
    }

    class User {
        var accessToken : Long = 0
        var userId : Int = 0
        var name : String? = null
    }
}