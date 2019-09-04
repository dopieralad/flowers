package pl.dopieralad.university.ma.flowers

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.main_activity.*
import pl.dopieralad.university.ma.flowers.flower.Flower
import pl.dopieralad.university.ma.flowers.flower.FlowerListAdapter
import pl.dopieralad.university.ma.flowers.flower.FlowerViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        setSupportActionBar(bar)

        val recyclerAdapter = FlowerListAdapter(this)
        val recyclerView: RecyclerView = findViewById(R.id.flower_list)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val flowerViewModel = ViewModelProvider(this).get(FlowerViewModel::class.java)
        flowerViewModel.getAll().observe(this, Observer { recyclerAdapter.flowers = it })

        fab.setOnClickListener { view ->
            val flower = Flower(name = "Monstera", species = "Monster!")

            flowerViewModel.insert(flower)

            val snackbar = Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_INDEFINITE)
            snackbar.duration = 2000
            snackbar.setAction("Dismiss") { snackbar.dismiss() }
            snackbar.show()
        }
    }
}
