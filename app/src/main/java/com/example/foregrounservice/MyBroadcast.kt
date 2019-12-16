package com.example.foregrounservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcast :BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startService(Intent(context,ForegroundSerice::class.java).putExtra("stop","stop"))
        Toast.makeText(context,"aa",Toast.LENGTH_SHORT).show()
    }
}