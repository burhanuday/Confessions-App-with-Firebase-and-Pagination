package com.burhanuday.confessionsapp.Utility

import android.content.Context
import android.telephony.TelephonyManager
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

/**
 * Created by burhanuday on 28-10-2018.
 */

class Constants{

    companion object {
        val collection: String = "ConfessionsApp"
        val posts: String = "Posts"

        fun getTime(): String{
            val date = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
            return dateFormat.format(date)
        }
    }

}