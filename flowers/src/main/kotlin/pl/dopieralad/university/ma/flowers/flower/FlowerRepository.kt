package pl.dopieralad.university.ma.flowers.flower

import android.content.Context
import androidx.lifecycle.LiveData
import pl.dopieralad.university.ma.flowers.database.FlowersDatabase
import pl.dopieralad.university.ma.flowers.utils.Asynchronous

class FlowerRepository(context: Context) {

    private val flowerDao: FlowerDao
    private val allFlowers: LiveData<List<FlowerWithSpecies>>

    init {
        val flowersDatabase = FlowersDatabase.getInstance(context)

        flowerDao = flowersDatabase.flowerDao()
        allFlowers = flowerDao.getAll()
    }

    fun insert(flower: Flower) = Asynchronous { flowerDao.insert(flower) }

    fun getAll() = allFlowers

    fun delete(flower: Flower) = Asynchronous { flowerDao.delete(flower) }
}
