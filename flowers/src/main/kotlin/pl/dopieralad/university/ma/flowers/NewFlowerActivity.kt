package pl.dopieralad.university.ma.flowers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.new_flower_activity.*
import pl.dopieralad.university.ma.flowers.flower.Flower
import pl.dopieralad.university.ma.flowers.utils.Extras
import pl.dopieralad.university.ma.flowers.utils.ResultCode
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.dopieralad.university.ma.flowers.flower.FlowerViewModel
import pl.dopieralad.university.ma.flowers.species.Species
import pl.dopieralad.university.ma.flowers.species.SpeciesListAdapter
import pl.dopieralad.university.ma.flowers.species.SpeciesViewModel
import java.util.function.Consumer

class NewFlowerActivity : AppCompatActivity() {

    private lateinit var speciesViewModel: SpeciesViewModel
    private var selectedSpecies: Species? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.new_flower_activity)
        setSupportActionBar(bar)

        speciesViewModel = ViewModelProvider(this).get(SpeciesViewModel::class.java)
        speciesViewModel.getAll().observe(this, Observer { println("Species: $it") })

        setupKeyboardBehavior()
        setupFlowerList()
        setupFab()
        setupBar()
    }

    private fun setupKeyboardBehavior() {
        coordinator.setOnTouchListener { view, _ ->
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun setupFlowerList() {
        val recyclerAdapter = SpeciesListAdapter(this, Consumer { selectedSpecies = it })
        val recyclerView: RecyclerView = findViewById(R.id.species_list)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        speciesViewModel = ViewModelProvider(this).get(SpeciesViewModel::class.java)
        speciesViewModel.getAll().observe(this, Observer { recyclerAdapter.species = it })
    }

    private fun setupFab() {
        fab.setOnClickListener { onFabClicked() }
    }

    private fun setupBar() {
        bar.setNavigationOnClickListener { finish() }
    }

    private fun onFabClicked() {
        selectedSpecies?.let {
            val flowerName = flower_name.text.toString()
            val flower = Flower(name = flowerName, speciesId = it.id)

            val intent = Intent()
            intent.putExtra(Extras.FLOWER.name, flower)

            setResult(ResultCode.OK.ordinal, intent)

            finish()
        }
    }
}
