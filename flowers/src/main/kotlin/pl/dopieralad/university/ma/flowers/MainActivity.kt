package pl.dopieralad.university.ma.flowers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_activity.*
import pl.dopieralad.university.ma.flowers.flower.Flower
import pl.dopieralad.university.ma.flowers.flower.FlowerListAdapter
import pl.dopieralad.university.ma.flowers.flower.FlowerViewModel
import pl.dopieralad.university.ma.flowers.reminder.ReminderReceiver
import pl.dopieralad.university.ma.flowers.utils.Extras
import pl.dopieralad.university.ma.flowers.utils.RequestCode
import java.util.function.Consumer
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var flowerViewModel: FlowerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        setSupportActionBar(bar)

        setupReminder()
        setupFlowerList()
        setupFab()
    }

    private fun setupReminder() {

        val intent = Intent(applicationContext, ReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.cancel(pendingIntent)
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                60_000L,
                pendingIntent
        )

        Log.i("MainActivity", "Set up!")
    }

    private fun setupFlowerList() {
        val recyclerAdapter = FlowerListAdapter(this, Consumer { onFlowerWatered(it) }, Consumer { onFlowerDeleted(it) })

        val recyclerView: RecyclerView = findViewById(R.id.flower_list)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val noFlowerInfo: LinearLayout = findViewById(R.id.no_flower_info)

        flowerViewModel = ViewModelProvider(this).get(FlowerViewModel::class.java)
        flowerViewModel.getAll().observe(this, Observer {
            recyclerAdapter.flowers = it
            if (it.isEmpty()) {
                noFlowerInfo.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                noFlowerInfo.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        })
    }

    private fun setupFab() {
        fab.setOnClickListener { this.onFabClicked() }
    }

    private fun onFabClicked() {
        val intent = Intent(this@MainActivity, NewFlowerActivity::class.java)
        startActivityForResult(intent, RequestCode.ADD_FLOWER.ordinal)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            RequestCode.ADD_FLOWER.ordinal -> onFlowerAdded(data)
        }
    }

    private fun onFlowerAdded(data: Intent?) {
        data?.let {
            val flower = data.getSerializableExtra(Extras.FLOWER.name) as Flower?
            flower?.let {
                flowerViewModel.insert(it)
                val view: RecyclerView = findViewById(R.id.flower_list)
                Snackbar.make(view, getString(R.string.flower_created, it.name), 2000).show()
            }
        }
    }

    private fun onFlowerWatered(flower: Flower) {
        flower.let {
            it.lastWatered = Date()
            flowerViewModel.update(it)
            val view: RecyclerView = findViewById(R.id.flower_list)
            Snackbar.make(view, getString(R.string.flower_watered, it.name), 2000).show()
        }
    }

    private fun onFlowerDeleted(flower: Flower) {
        MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered)
                .setTitle(getString(R.string.are_you_sure))
                .setMessage(getString(R.string.delete_flower_confirmation, flower.name))
                .setNegativeButton(getString(R.string.no), null)
                .setPositiveButton(getString(R.string.yes)) { _, _ ->
                    flowerViewModel.delete(flower)
                    val view: RecyclerView = findViewById(R.id.flower_list)
                    Snackbar.make(view, getString(R.string.flower_deleted, flower.name), 2000).show()
                }
                .show()
    }
}
