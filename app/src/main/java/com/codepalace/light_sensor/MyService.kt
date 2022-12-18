package com.codepalace.light_sensor

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import org.greenrobot.eventbus.EventBus
import java.lang.UnsupportedOperationException

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        EventBus.getDefault().register(this)
        //首次進入執行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //Android 8.0以上要設定前景通知才能背景執行
            //初始化通知
            val mNotificationManager = getSystemService(NotificationManager::class.java)
            val mChannel = NotificationChannel("01", "位置服務", NotificationManager.IMPORTANCE_MIN)
            mChannel.enableVibration(false) //是否開啟震動
            mChannel.enableLights(false) //是否開啟燈號閃爍
            mNotificationManager.createNotificationChannel(mChannel)
            val notification = NotificationCompat.Builder(this, "01")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("藍牙服務")
                .setContentText("背景服務執行中")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(getColor(R.color.black))
                .build()
            // 服務通知第一個Int參數ID不能為0
            startForeground(1, notification)
        }
        //LogUtil.e("NewBleService", "服務已建立")
    }

    override fun onBind(intent: Intent?): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        object : Thread() {
            override fun run() {
                super.run()

                //...要做的事在這執行...
            }
        }.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        //service關閉後重開方式↓
        startService(Intent(this, MyService::class.java))
    }


}


