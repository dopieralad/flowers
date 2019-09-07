package pl.dopieralad.university.ma.flowers.flower

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class FlowerViewModel(application: Application) : AndroidViewModel(application) {

    private val flowerRepository: FlowerRepository = FlowerRepository(application)

    fun insert(flower: Flower) = flowerRepository.insert(flower)

    fun getAll() = flowerRepository.getAll()

    fun update(flower: Flower) = flowerRepository.update(flower)

    fun delete(flower: Flower) = flowerRepository.delete(flower)
}
