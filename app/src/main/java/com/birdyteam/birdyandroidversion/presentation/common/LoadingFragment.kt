package com.birdyteam.birdyandroidversion.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.birdyteam.birdyandroidversion.R
import dagger.Module

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */

@Module
class LoadingFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }
}