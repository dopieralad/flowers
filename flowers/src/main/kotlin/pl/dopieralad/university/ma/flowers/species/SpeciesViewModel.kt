package pl.dopieralad.university.ma.flowers.species

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SpeciesViewModel(application: Application) : AndroidViewModel(application) {

    private val speciesRepository = SpeciesRepository(application)

    fun getAll() = speciesRepository.getAll()
}
