package com.birdyteam.birdyandroidversion.model.authorized

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.birdyteam.birdyandroidversion.R

class MenuFragment : Fragment(), View.OnClickListener {

    private lateinit var mView: View
    private lateinit var settingsBtn: ImageView
    private lateinit var profileBtn : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_menu, container, false)
        settingsBtn = mView.findViewById(R.id.Settings)
        settingsBtn.setOnClickListener(this)
        profileBtn = mView.findViewById(R.id.UserProfile)
        profileBtn.setOnClickListener (this)
        return mView
    }

    override fun onClick(p0: View?) {
        Log.d(AuthorizedActivity.TAG, "Clicked : ${p0?.id}")
        (activity as? AuthorizedActivity)?.menuClicked(p0?.id)
    }
}