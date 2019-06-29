package com.birdyteam.birdyandroidversion.model.authorized

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.model.user.UserFactory

class ProfileFragment : Fragment() {

    companion object {
        private const val REQUEST_PICK_PICTURE = 1
    }

    private lateinit var mView: View
    private lateinit var avatar: ImageView
    private lateinit var userId: TextView
    private lateinit var userName: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_profile, container, false)
        avatar = mView.findViewById(R.id.ProfileImage)
        avatar.setOnClickListener {

            val getIntent = Intent(Intent.ACTION_GET_CONTENT)
            getIntent.type = "image/*"

            val mIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            mIntent.type = "image/*"

            val chooser = Intent.createChooser(getIntent, "Select Image")
            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(mIntent))

            startActivityForResult(chooser, REQUEST_PICK_PICTURE)
        }

        userId = mView.findViewById(R.id.UserId)
        userId.text = UserFactory.currentUser?.userId.toString()

        userName = mView.findViewById(R.id.UserName)
        userName.text = UserFactory.currentUser?.name


        return mView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_PICK_PICTURE -> {
                if (resultCode == Activity.RESULT_OK) {
                    data ?: return
                    avatar.setImageURI(data.data)
                }
            }
        }
    }
}