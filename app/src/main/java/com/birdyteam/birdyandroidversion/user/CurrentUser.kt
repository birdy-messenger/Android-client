package com.birdyteam.birdyandroidversion.user

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 08.07.2019
 */
object CurrentUser {

    const val USER = "current.user"
    const val SAVE_ID = "save.id"
    const val SAVE_TOKEN = "save.token"

    private var mId: Int? = null
    var id: Int
        get() {
            mId ?: throw IllegalStateException("User doesn't exist")
            return mId!!
        }
        set(value) {
            mId = value
        }

    private var mToken: Long? = null
    var token: Long
        get() {
            mToken ?: throw IllegalStateException("User doesn't exist")
            return mToken!!
        }
        set(value) {
            mToken = value
        }
}