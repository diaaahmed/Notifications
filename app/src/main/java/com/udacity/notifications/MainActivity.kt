package com.udacity.notifications

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
           startActivity(Intent(this,NotificationProgress::class.java))
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun sendChannel1()
    {
        if(!compat.areNotificationsEnabled())
        {
            openNotificationSetting()
        }
        else
        {
            var intent = Intent(this,MainActivity::class.java)

            var pending_intent = PendingIntent.getActivity(this,
                0,intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

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

    private fun openNotificationSetting()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            var intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
            intent.putExtra(Settings.EXTRA_APP_PACKAGE,packageName)
            startActivity(intent)
        }
        else
        {
            var intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.setData(Uri.parse("package:"+packageName))
            startActivity(intent)
        }
    }
}