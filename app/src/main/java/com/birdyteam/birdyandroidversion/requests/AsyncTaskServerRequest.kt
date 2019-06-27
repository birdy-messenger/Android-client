package com.birdyteam.birdyandroidversion.requests

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import com.birdyteam.birdyandroidversion.model.InterfaceAccessAsync
import com.birdyteam.birdyandroidversion.model.LoginActivity
import com.birdyteam.birdyandroidversion.view.LoadingFragment
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection

class AsyncTaskServerRequest(
    private val showDialog : Boolean = false,
    private val accessAsync: InterfaceAccessAsync?,
    private val requestId : RequestID,
    private val requestArgs : Array<String>?
    )
    : AsyncTask<Void, Void, String>() {

    private var dialog : LoadingFragment? = null

    override fun onPreExecute() {
        super.onPreExecute()
        val fm = (accessAsync as AppCompatActivity).supportFragmentManager
        if(showDialog)
            dialog = LoadingFragment()
        dialog?.show(fm, LoginActivity.LOADING_TAG)
    }

    override fun doInBackground(vararg p0: Void?): String {
        val url = BirdyRequestUtils.createRequest(requestId, requestArgs)
        val connection = url.openConnection() as HttpURLConnection
        try {
            val input : InputStream = if(connection.responseCode != HttpURLConnection.HTTP_OK)
                connection.errorStream
            else
                connection.inputStream
            val out = ByteArrayOutputStream()
            var bytesRead : Int
            val buffer = ByteArray(1024)
            while(input.read(buffer).also { bytesRead = it } > 0) {
                out.write(buffer, 0, bytesRead)
            }
            out.close()
            return out.toString()
        } catch (e : Exception) {
            return ""
        }
        finally {
            connection.disconnect()
        }
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        if(dialog?.dialog?.isShowing == true) {
            dialog?.dismissAllowingStateLoss()
        }
        accessAsync?.registerAccess(result)
    }
}