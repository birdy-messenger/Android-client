package com.birdyteam.birdyandroidversion.di.modules

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 11.07.2019
 */
@Module
class AppModule(private val context: Context) {
    @Provides
    fun provideContext() : Context = context

    @Provides
    fun provideResources() : Resources = context.resources
}