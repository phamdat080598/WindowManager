package com.example.foregrounservice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Handler
import android.os.IBinder
import android.view.*
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.item_message.view.*
import androidx.core.view.marginTop


class ForegroundSerice : Service(),View.OnTouchListener {
    lateinit var manager : WindowManager
    lateinit var groupLayout:GroupLayout
    lateinit var layoutParams: WindowManager.LayoutParams
    var previousX:Int = 0
    var previousY:Int =0
    var startX:Float=0F
    var startY:Float=0F
    var check = true
    lateinit var view :TextView
    val handler=Handler()

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
        startForeground(1,MyNotification.createNotification(this))
        Toast.makeText(this,"onCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent?.extras?.get("start").toString()=="1"){
            initView()
            Thread(Runnable {
                var i=0
                while(check) {
                    handler.post(Runnable {
                        view.setText("Showaaaaa aaaaaaaaaaaa: $i")
                    })
                    Thread.sleep(1000)
                    i++
                }
            }).start()
        }else{
            stopSelf()
            //manager.removeViewImmediate(groupLayout)
        }
        Toast.makeText(this,"onStartCommand",Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        manager.removeView(groupLayout)
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show()
        check =false
    }

    fun initView(){
        manager = getSystemService(WINDOW_SERVICE) as WindowManager
        createView()
        manager.addView(groupLayout,layoutParams)
    }
    fun createView(){
        groupLayout = GroupLayout(this)
        groupLayout.setOnTouchListener(this)
        val view1 = View.inflate(this,R.layout.item_message,groupLayout)
        view = view1.message

        layoutParams = WindowManager.LayoutParams()
        layoutParams.x=0
        layoutParams.y=0
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.gravity = Gravity.CENTER
        layoutParams.format = PixelFormat.TRANSLUCENT
        layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val layoutParams1: WindowManager.LayoutParams=layoutParams
//        if(v?.id==R.id.layout) {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    previousX = layoutParams1.x
                    previousY = layoutParams1.y

                    startX = event.rawX
                    startY = event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    val x = event.rawX - startX
                    val y = event.rawY - startY

                    layoutParams1.x = (x + previousX).toInt()
                    layoutParams1.y = (y + previousY).toInt()

                    manager.updateViewLayout(groupLayout, layoutParams1)
                }
                MotionEvent.ACTION_OUTSIDE -> {
                }
            }
//        }
        return false
    }

}