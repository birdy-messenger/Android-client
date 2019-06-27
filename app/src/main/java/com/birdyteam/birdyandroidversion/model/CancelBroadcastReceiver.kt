package com.birdyteam.birdyandroidversion.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CancelBroadcastReceiver(private val listener : OnCancelBroadcast) : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        listener.onCancel()
    }

    interface OnCancelBroadcast {
        fun onCancel()
    }
}