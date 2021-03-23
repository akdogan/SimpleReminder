package com.akdogan.simplereminder

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.AlarmManagerCompat
import androidx.core.app.NotificationCompat
import com.akdogan.simplereminder.utils.generateNotificationId
import com.google.android.material.textfield.TextInputLayout
import java.util.*


fun NotificationManager.sendNotification(messageBody: String, appContext: Context){
    val builder = NotificationCompat.Builder(
        appContext,
        SINGLE_CHANNEL_ID
    ).setSmallIcon(R.drawable.not_icon)
        .setContentTitle("Test Notification")
        .setContentText(messageBody)


    notify(generateNotificationId(), builder.build())


}

/**
 * Creates a Pending intent and and an alarm to send a Notification based on it
 * @param app Application, cannot be null
 * @param message The Notfication Body
 * @return Returns an id which can be used to cancel the notification
 */
fun postNotification(app: Application, message: String, triggerTime: Long): Int {
    val notifyIntent = Intent(app, AlarmReceiver::class.java)

    notifyIntent.putExtra(INTENT_EXTRA_MESSAGE_KEY, message)
    val rCode = generateNotificationId()

    val notifyPendingIntent = PendingIntent.getBroadcast(
        app,
        rCode,
        notifyIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    AlarmManagerCompat.setExactAndAllowWhileIdle(
        app.getSystemService(Context.ALARM_SERVICE) as AlarmManager,
        AlarmManager.RTC,
        triggerTime,
        notifyPendingIntent
    )
    return rCode
}

fun cancelNotification(app: Application, id: Int){
    val notifyIntent = Intent(app, AlarmReceiver::class.java)
    val pIntent = PendingIntent.getBroadcast(
        app,
        id,//REQUEST_CODE,
        notifyIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )//.cancel()
    val alarmManager = app.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(pIntent)
}