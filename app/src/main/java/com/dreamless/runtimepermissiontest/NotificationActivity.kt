package com.dreamless.runtimepermissiontest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_notification.*
import java.lang.Exception

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("high", "High", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }
        btn_send.setOnClickListener {
            try {
                val pi = PendingIntent.getActivity(
                    this,
                    0,
                    Intent(this, NotificationActivity2::class.java),
                    0
                )
                val notification = NotificationCompat.Builder(this, "high")
                    .setContentTitle("标题")
//                    .setContentText("内容")
                    .setSmallIcon(R.drawable.small_icon)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            resources, R.drawable.large_icon
                        )
                    )
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setStyle(
                        NotificationCompat.BigPictureStyle().bigPicture(
                            BitmapFactory.decodeResource(
                                resources, R.drawable.big_image
                            )
                        )
                    )
                    .build()
                manager.notify(1, notification)
            } catch (ex: Exception) {
                Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}