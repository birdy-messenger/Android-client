package com.birdyteam.birdyandroidversion.domain.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.birdyteam.birdyandroidversion.data.network.api.app.response.AuthResponse
import com.birdyteam.birdyandroidversion.data.repository.UserAuthInfoRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.IllegalStateException

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
class UserAuthInfoRepositoryImpl(context: Context) : UserAuthInfoRepository {

    companion object {
        const val USER = "current.user"
        const val SAVE_ID = "save.id"
        const val SAVE_TOKEN = "save.token"
    }

    private val sharedPreferences =context.getSharedPreferences(USER, Context.MODE_PRIVATE)

    override fun saveAuthInfo(info: AuthResponse): Completable = Completable.fromAction {
        sharedPreferences.edit {
            clear()
            putInt(SAVE_ID, info.id)
            putLong(SAVE_TOKEN, info.token)
            apply()
        }
    }

    override fun getAuthInfo(): Single<AuthResponse> = Single.fromCallable {
        val id = sharedPreferences.getInt(SAVE_ID, -1)
        val token = sharedPreferences.getLong(SAVE_TOKEN, -1L)
        if (id == -1 || token == -1L)
            throw IllegalStateException("Missing saved auth info")
        AuthResponse(id, token)
    }
}