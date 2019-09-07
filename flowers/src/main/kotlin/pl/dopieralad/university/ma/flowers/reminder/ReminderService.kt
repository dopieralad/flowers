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
import pl.dopieralad.university.ma.flowers.database.FlowersDatabase
import pl.dopieralad.university.ma.flowers.flower.Flower
import pl.dopieralad.university.ma.flowers.flower.FlowerDao
import java.text.DateFormat
import java.text.SimpleDateFormat

class ReminderService : IntentService("reminder-service") {

    private val channelId = "watering_reminder"
    private lateinit var flowerDao: FlowerDao
    private lateinit var dateFormat: DateFormat

    override fun onCreate() {
        super.onCreate()

        val flowersDatabase = FlowersDatabase.getInstance(this)
        flowerDao = flowersDatabase.flowerDao()

        dateFormat = SimpleDateFormat(getString(R.string.date_format), Locale.getDefault())

        createNotificationChannel()
        Log.i("ReminderService", "Created!")
    }

    override fun onHandleIntent(intent: Intent?) {
        val unwateredFlowers = flowerDao.getUnwatered()
        Log.i("ReminderService", "There are ${unwateredFlowers.size} unwatered flowers!")
        unwateredFlowers.forEach {
            Log.i("ReminderService", "Unwatered: ${it}")
        }
        sendNotifications(unwateredFlowers)
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

    private fun sendNotifications(unwateredFlowers: List<Flower>) {
        unwateredFlowers.forEach(this::sendNotification)
    }

    private fun sendNotification(flower: Flower) {
        val id = System.currentTimeMillis().toInt()

        val intent = getMainActivityIntent()

        val contentText = if (flower.lastWatered.time != 0L) {
            getString(R.string.last_watered, dateFormat.format(flower.lastWatered))
        } else {
            getString(R.string.never_watered)
        }

        val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_monstera_round)
                .setContentTitle(getString(R.string.flower_requires_watering, flower.name))
                .setContentText(contentText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(intent)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(id, builder.build())
        }
    }

    private fun getMainActivityIntent() = TaskStackBuilder.create(this).run {
        val intent = Intent(this@ReminderService, MainActivity::class.java)
        addNextIntentWithParentStack(intent)
        getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onDestroy() {
        Log.i("ReminderService", "Destroyed!")
        super.onDestroy()
    }
}
