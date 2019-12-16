package com.example.foregrounservice

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyNotification {
    companion object {
        val CHANNEL_ID = "chanel"

        @SuppressLint("WrongConstant", "NewApi")
        public fun createNotification(context: Context): Notification {
            val intentBroadcast = Intent(context, MyBroadcast::class.java)
            val pendingContent =
                PendingIntent.getBroadcast(context, 0, intentBroadcast, 0)
            val notification: Notification
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notification = Notification.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingContent)
                    .setContentText("Sing my song")
                    .setContentTitle("Play music")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .build()
            } else {
                notification = Notification.Builder(context)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingContent)
                    .setContentText("Sing my song")
                    .setContentTitle("Play music")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .build()
            }
            return notification
        }
    }
}