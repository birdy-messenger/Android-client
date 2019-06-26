package com.birdyteam.birdyandroidversion.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.birdyteam.birdyandroidversion.R


class LoadingFragment : AppCompatDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!,
            R.style.ThemeOverlay_AppCompat_Dialog
        )
        val view = activity!!.layoutInflater.inflate(R.layout.loading_dialog_fragment, null)
        isCancelable = false
        return builder.setView(view)
            .create()
    }
}