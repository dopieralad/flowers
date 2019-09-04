package pl.dopieralad.university.ma.flowers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.new_flower_activity.*

class NewFlowerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.new_flower_activity)

        setSupportActionBar(bar)

        bar.setNavigationOnClickListener { finish() }
    }
}
