package com.suraksha.android.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.suraksha.android.view.activity.SurakshaLaunchActivity
import com.suraksha.android.view.utility.NotificationActions
import com.suraksha.android.view_model.BaseViewModel
import com.suraksha.app.R


class SurakshaFirebaseService : FirebaseMessagingService() {
    private var viewModel: BaseViewModel?=null
    val TAG = javaClass.simpleName

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            sendNotification(remoteMessage)
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(remoteMessage)
        }
    }

    private fun sendNotification(message: RemoteMessage) {

        var notification = message?.notification
        val pendingCount = message.data["remainingEnrollCount"]
        val timeStamp =    message.data["timestamp"]

        val intent = Intent(this, SurakshaLaunchActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)


        val channelId = getString(R.string.notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)

            .setContentTitle(notification?.title)
            .setContentText(notification?.body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
//TODO Set small icon
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                getString(R.string.notification_channel_id),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify( 0  /* ID of notification */, notificationBuilder.build())

        val bundle= bundleOf(Pair(NotificationActions.BundleKey.TITLE,notification?.title),
            Pair(NotificationActions.BundleKey.DESC,notification?.body),Pair(NotificationActions.BundleKey.PENDINGCOUNT,pendingCount))
        sendBroadcast(NotificationActions.ACTION_APPROVE_STUDENT,bundle)

    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //send new token to server
    }


    fun sendBroadcast(action:String,bundle:Bundle) {
        val intent = Intent(action)
        intent.putExtra(NotificationActions.BundleKey.DATA, bundle)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}