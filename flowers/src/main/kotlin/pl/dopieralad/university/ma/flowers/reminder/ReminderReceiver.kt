package pl.dopieralad.university.ma.flowers.reminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("ReminderReceiver", "Received!")

        context?.let {
            val reminderIntent = Intent(it, ReminderService::class.java)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                it.startForegroundService(reminderIntent)
            } else {
                it.startService(reminderIntent)
            }
        }

        Log.i("ReminderReceiver", "Handled!")
    }
}
