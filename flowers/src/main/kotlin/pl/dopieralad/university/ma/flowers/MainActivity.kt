package pl.dopieralad.university.ma.flowers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_activity.*
import pl.dopieralad.university.ma.flowers.flower.Flower
import pl.dopieralad.university.ma.flowers.flower.FlowerListAdapter
import pl.dopieralad.university.ma.flowers.flower.FlowerViewModel
import pl.dopieralad.university.ma.flowers.utils.Extras
import pl.dopieralad.university.ma.flowers.utils.RequestCode


class MainActivity : AppCompatActivity() {

    private lateinit var flowerViewModel: FlowerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        setSupportActionBar(bar)

        val recyclerAdapter = FlowerListAdapter(this)
        val recyclerView: RecyclerView = findViewById(R.id.flower_list)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        flowerViewModel = ViewModelProvider(this).get(FlowerViewModel::class.java)
        flowerViewModel.getAll().observe(this, Observer { recyclerAdapter.flowers = it })

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewFlowerActivity::class.java)
            startActivityForResult(intent, RequestCode.ADD_FLOWER.ordinal)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            RequestCode.ADD_FLOWER.ordinal -> handleFlowerAdded(data)
        }
    }

    private fun handleFlowerAdded(data: Intent?) {
        data?.let {
            val flower = data.getSerializableExtra(Extras.FLOWER.name) as Flower?
            flower?.let {
                flowerViewModel.insert(it)
                val view: RecyclerView = findViewById(R.id.flower_list)
                Snackbar.make(view, "Flower '${it.name}' created!", 2000).show()
            }
        }
    }
}
