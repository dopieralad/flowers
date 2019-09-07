package pl.dopieralad.university.ma.flowers.reminder

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import pl.dopieralad.university.ma.flowers.MainActivity
import java.util.*
import pl.dopieralad.university.ma.flowers.R

class ReminderService : IntentService("reminder-service") {

    private val channelId = "watering_reminder"

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
        Log.i("ReminderService", "Created!")
    }

    override fun onHandleIntent(intent: Intent?) {
        sendNotification()
        Log.i("ReminderService", "Handled at '${Date()}'!")
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val channel = NotificationChannel(channelId, "Podlewanie", NotificationManager.IMPORTANCE_DEFAULT).apply {
                this.description = "Powiadomienia o konieczności podlania kwiatów"
            }

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        val id = System.currentTimeMillis().toInt()

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_monstera_round)
                .setContentTitle("Notyfikacja numer $id")
                .setContentText("Much longer text that cannot fit one line...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(id, builder.build())
        }
    }
}
