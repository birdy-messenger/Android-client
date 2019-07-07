package com.birdyteam.birdyandroidversion.di.modules

import com.birdyteam.birdyandroidversion.fragment.LoadingFragment
import dagger.Module
import dagger.Provides

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */

@Module
object LoadingModule {
    @JvmStatic
    @Provides
    fun provideLoadingFragment() : LoadingFragment = LoadingFragment()
}