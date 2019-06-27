package com.birdyteam.birdyandroidversion.model

interface InterfaceAccessAsync {
    companion object {
        const val CANCEL = "cancel.dialog"
    }

    var cancelReceiver : CancelBroadcastReceiver?
    fun registerAccess(access : String)
}