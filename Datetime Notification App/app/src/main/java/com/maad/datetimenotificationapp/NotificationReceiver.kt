package com.maad.datetimenotificationapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("trace", "received...")
        sendNotification(
            context!!,
            intent?.getStringExtra("title")!!,
            intent.getStringExtra("text")!!
        )
    }

}