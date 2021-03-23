package com.akdogan.simplereminder

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Notification triggered", Toast.LENGTH_SHORT).show()
        val notMan = context?.getSystemService(NotificationManager::class.java)
        val extras = intent?.extras
        val messageBody = extras?.getString(INTENT_EXTRA_MESSAGE_KEY) ?: "Intent was empty"

        notMan?.sendNotification(messageBody, context)
    }
}
