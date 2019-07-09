package com.birdyteam.birdyandroidversion.domain.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.birdyteam.birdyandroidversion.data.network.api.app.input.Info
import com.birdyteam.birdyandroidversion.data.repository.UserAuthInfoRepository
import io.reactivex.Completable

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
class UserAuthInfoRepositoryImpl(private val sharedPreferences: SharedPreferences) : UserAuthInfoRepository {

    companion object {
        const val USER = "current.user"
        const val SAVE_ID = "save.id"
        const val SAVE_TOKEN = "save.token"
    }

    override fun saveInfo(info: Info): Completable = Completable.fromAction {
        sharedPreferences.edit {
            clear()
            putInt(SAVE_ID, info.id)
            putLong(SAVE_TOKEN, info.token)
            apply()
        }
    }
}