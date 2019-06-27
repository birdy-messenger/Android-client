package com.birdyteam.birdyandroidversion.hash

import android.util.Log
import com.birdyteam.birdyandroidversion.model.LoginActivity
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MD5Utils {
    companion object {
        fun createMd5(arg : String) : String{
            lateinit var md : MessageDigest
            var digest = ByteArray(0)
            try {
                md = MessageDigest.getInstance("MD5")
                md.reset()
                md.update(arg.toByteArray())
                digest = md.digest()
            } catch (e : NoSuchAlgorithmException) {
                Log.d(LoginActivity.TAG, "$e")
            }
            val bigInt = BigInteger(1, digest)
            var md5Hex = bigInt.toString(16)
            while(md5Hex.length < 32)
                md5Hex = "0$md5Hex"
            Log.d(LoginActivity.TAG, md5Hex)
            return md5Hex
        }
    }
}
