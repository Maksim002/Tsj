package com.timelysoft.tsjdomcom.service.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.timelysoft.tsjdomcom.R

class FireBaseService : FirebaseMessagingService(){
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        showNotification(p0.notification?.title.toString(), p0.notification?.body.toString())

    }
    private fun showNotification(title: String, message: String) {

        FirebaseMessaging.getInstance().subscribeToTopic("AndroidTsjDomCom")

        val notChannelId = "com.timelysoft.tsjdomcom"
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val c =
                NotificationChannel(notChannelId, "AndroidTsjDomCom", NotificationManager.IMPORTANCE_DEFAULT)
            c.description = "AndroidTsjDomCom"
            c.enableLights(true)
            c.lightColor = Color.BLUE
            notificationManager.createNotificationChannel(c)

        }

        val builder = NotificationCompat.Builder(this,notChannelId)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_accept)
            .setAutoCancel(true)
            .setContentText(message)


        val manager = NotificationManagerCompat.from(this)
        manager.notify(1998, builder.build())


    }
}