package com.codepalace.light_sensor
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class NotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(sensor: Float) {
        val activityIntent = Intent(context, MainActivity::class.java)

        val notification = NotificationCompat.Builder(context, COUNTER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setContentTitle("brightness")
            .setContentText("Too dark! brightness is lower than $sensor")
            .setAutoCancel(true)
            .build()
        notificationManager.notify(1, notification)

    }

    companion object {
        const val COUNTER_CHANNEL_ID = "counter_channel"
    }
}