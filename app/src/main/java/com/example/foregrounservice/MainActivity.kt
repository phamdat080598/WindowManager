package com.example.foregrounservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()

        btnStart.setOnClickListener {
            startService(Intent(this@MainActivity,ForegroundSerice::class.java).putExtra("start","1"))
        }
    }

    fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Phat nhạc"
            val description = "Phạm Văn Đạt"
            val importance = NotificationManager.IMPORTANCE_DEFAULT;
            val channel = NotificationChannel(MyNotification.CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system;
            //you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService (NotificationManager::class.java);
            // Đăng kí Chanel cho notification
            notificationManager.createNotificationChannel(channel);
        }
    }
}
