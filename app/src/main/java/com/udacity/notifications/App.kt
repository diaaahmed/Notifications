package com.udacity.notifications

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build


class App: Application()
{

    companion object{
         val CHANNEL_ID = "CHANNEL1"
         val CHANNEL_ID2 = "CHANNEL2"
    }
    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            var channel1 = NotificationChannel(
                CHANNEL_ID,"Channel Name1",NotificationManager.IMPORTANCE_HIGH
            )

            channel1.description = "This is channel1"
            channel1.setShowBadge(false)

            var channel2 = NotificationChannel(
                CHANNEL_ID2,"Channel Name2",NotificationManager.IMPORTANCE_HIGH
            )

            channel2.description = "This is channel2"

            var manager:NotificationManager = getSystemService(NotificationManager::class.java)

            manager.createNotificationChannel(channel1)
            manager.createNotificationChannel(channel2)


        }
    }
}