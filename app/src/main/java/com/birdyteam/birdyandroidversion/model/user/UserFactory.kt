package com.birdyteam.birdyandroidversion.model.user

class UserFactory private constructor(){

    companion object {
        var currentUser : User? = null
    }

    inner class User constructor(){
        var accessToken : Int = 0
        var userId : Int = 0
        var name : String? = null
    }
}