package com.udacity.notifications

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.udacity.notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private val ui by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var compat:NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)

        compat = NotificationManagerCompat.from(this)

        ui.sendChannel1.setOnClickListener{
            sendChannel1()
        }

        ui.sendChannel2.setOnClickListener{
            sendChannel2()
        }
    }

    private fun sendChannel1()
    {

        var intent = Intent(this,MainActivity::class.java)

        var pending_intent = PendingIntent.getActivity(this,
        0,intent,PendingIntent.FLAG_IMMUTABLE)

        var largeIcon = BitmapFactory.decodeResource(resources,R.drawable.test)

        // Style for image
        val bigPicStyle = NotificationCompat.BigPictureStyle()
            .bigPicture(largeIcon)
            .bigLargeIcon(null)


        var notification = NotificationCompat.Builder(this,App.CHANNEL_ID)
            .setSmallIcon(R.drawable.alert)
            .setContentTitle("Channel 1 test")
            .setContentText("Message")
            .setLargeIcon(largeIcon)
            .setStyle(bigPicStyle)
            .setColor(Color.MAGENTA)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setContentIntent(pending_intent)
            .build()

        compat.notify(1,notification)
    }

    private fun sendChannel2()
    {
        var notification = NotificationCompat.Builder(this,App.CHANNEL_ID2)
            .setSmallIcon(R.drawable.baseline)
            .setContentTitle("Channel 2 test")
            .setContentText("Message channel2")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .build()

        compat.notify(2,notification)
    }
}