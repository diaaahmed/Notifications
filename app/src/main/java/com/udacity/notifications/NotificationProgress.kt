package com.udacity.notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.udacity.notifications.databinding.ActivityNotificationProgressBinding

class NotificationProgress : AppCompatActivity()
{
    private val ui by lazy{
        ActivityNotificationProgressBinding.inflate(layoutInflater)
    }

    lateinit var compat: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)

        compat = NotificationManagerCompat.from(this)

        ui.notificationProgress.setOnClickListener{
            sendChannel1()
        }

    }

    private fun sendChannel1()
    {
        var progressMax = 100

        var notification = NotificationCompat.Builder(this,App.CHANNEL_ID2)
            .setSmallIcon(R.drawable.baseline)
            .setContentTitle("Download")
            .setContentText("Download in progress")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setProgress(progressMax,0,false)
            .setAutoCancel(true)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setCategory(NotificationCompat.CATEGORY_ALARM)

        compat.notify(2,notification.build())

        Thread.sleep(1000)

        for(progress in 0..progressMax step 10)
        {
            notification.setProgress(progressMax,progress,false)
            compat.notify(2, notification.build())
            Thread.sleep(1000)
        }

        notification.setContentText("Download completed")
            .setProgress(0,0,false)
            .setOngoing(false)
        compat.notify(2, notification.build())

    }

}