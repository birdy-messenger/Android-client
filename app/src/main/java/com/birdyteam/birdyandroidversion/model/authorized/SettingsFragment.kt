package com.birdyteam.birdyandroidversion.model.authorized

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.birdyteam.birdyandroidversion.R

class SettingsFragment : Fragment() {

    private lateinit var mView : View
    private lateinit var logoutBtn : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_settings, container, false)

        logoutBtn = mView.findViewById(R.id.LogoutBtn)
        logoutBtn.setOnClickListener {
            (activity as? AuthorizedActivity)?.logout()
        }
        return mView
    }
}