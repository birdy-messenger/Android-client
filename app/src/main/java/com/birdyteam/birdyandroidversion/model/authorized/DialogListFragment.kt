package com.birdyteam.birdyandroidversion.model.authorized

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.birdyteam.birdyandroidversion.R

class DialogListFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_dialog_list, container, false)

        recyclerView = mView.findViewById(R.id.RecyclerView)
        return mView
    }
}