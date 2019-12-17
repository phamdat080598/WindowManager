package com.example.foregrounservice

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        initPermission()
        btnStart.setOnClickListener {
            startService(Intent(this@MainActivity,ForegroundSerice::class.java).putExtra("start","1"))
        }
        btnStop.setOnClickListener {
            stopService(Intent(this@MainActivity,ForegroundSerice::class.java))
        }
    }

    fun initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED) { //Permisson don't granted
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.SYSTEM_ALERT_WINDOW
                    )
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        "Permission isn't granted ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Permisson don't granted and dont show dialog again ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //Register permission
                requestPermissions(
                    arrayOf(Manifest.permission.SYSTEM_ALERT_WINDOW),
                    1
                )
            }
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
