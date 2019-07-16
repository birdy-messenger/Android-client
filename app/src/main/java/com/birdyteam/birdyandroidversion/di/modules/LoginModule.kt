package com.birdyteam.birdyandroidversion.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.birdyteam.birdyandroidversion.data.network.api.app.AuthenticationApi
import com.birdyteam.birdyandroidversion.data.repository.UserAuthInfoRepository
import com.birdyteam.birdyandroidversion.domain.interactor.ReadAuthInfoInteractor
import com.birdyteam.birdyandroidversion.domain.interactor.SignInInteractor
import com.birdyteam.birdyandroidversion.domain.repository.UserAuthInfoRepositoryImpl
import com.birdyteam.birdyandroidversion.domain.validation.ValidateLoginInput
import dagger.Module
import dagger.Provides

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
@Module
object LoginModule {

    @JvmStatic
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(UserAuthInfoRepositoryImpl.USER, Context.MODE_PRIVATE)
    }

    @JvmStatic
    @Provides
    fun provideValidateLoginInput(): ValidateLoginInput = ValidateLoginInput()

    @JvmStatic
    @Provides
    fun provideSignInInteractor(
        userAuthInfoRepository: UserAuthInfoRepository,
        authenticationApi: AuthenticationApi
    ): SignInInteractor {
        return SignInInteractor(userAuthInfoRepository, authenticationApi)
    }

    @JvmStatic
    @Provides
    fun provideCheckAuthorizedInteractor(
        userAuthInfoRepository: UserAuthInfoRepository
    ): ReadAuthInfoInteractor {
        return ReadAuthInfoInteractor(userAuthInfoRepository)
    }

    @JvmStatic
    @Provides
    fun provideUserAuthRepository(context: Context): UserAuthInfoRepository {
        return UserAuthInfoRepositoryImpl(context)
    }
}