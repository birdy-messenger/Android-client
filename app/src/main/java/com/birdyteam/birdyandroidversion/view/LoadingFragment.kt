package com.birdyteam.birdyandroidversion.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.model.InterfaceAccessAsync


class LoadingFragment : AppCompatDialogFragment() {
    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!,
            R.style.ThemeOverlay_AppCompat_Dialog
        )
        val view = activity!!.layoutInflater.inflate(R.layout.loading_dialog_fragment, null)
        isCancelable = true
        return builder.setView(view)
            .create()
    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        activity?.sendBroadcast(
            Intent(InterfaceAccessAsync.CANCEL)
        )
    }
}