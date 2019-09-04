package pl.dopieralad.university.ma.flowers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.new_flower_activity.*
import pl.dopieralad.university.ma.flowers.flower.Flower
import pl.dopieralad.university.ma.flowers.utils.Extras
import pl.dopieralad.university.ma.flowers.utils.ResultCode

class NewFlowerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.new_flower_activity)
        setSupportActionBar(bar)

        setupFab()
        setupBar()
    }

    private fun setupFab() {
        fab.setOnClickListener { onFabClicked() }
    }

    private fun setupBar() {
        bar.setNavigationOnClickListener { finish() }
    }

    private fun onFabClicked() {
        val flowerName = flower_name.text.toString()
        val flowerSpecies = flower_species.text.toString()
        val flower = Flower(name = flowerName, species = flowerSpecies)

        val intent = Intent()
        intent.putExtra(Extras.FLOWER.name, flower)

        setResult(ResultCode.OK.ordinal, intent)

        finish()
    }
}
