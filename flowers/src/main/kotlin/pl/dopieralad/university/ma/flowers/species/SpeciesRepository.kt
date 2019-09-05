package pl.dopieralad.university.ma.flowers.species

import android.content.Context
import androidx.lifecycle.LiveData
import pl.dopieralad.university.ma.flowers.database.FlowersDatabase

class SpeciesRepository(context: Context) {

    private val allSpecies: LiveData<List<Species>>

    init {
        val flowersDatabase = FlowersDatabase.getInstance(context)
        val speciesDao = flowersDatabase.speciesDao()

        allSpecies = speciesDao.getAll()
    }

    fun getAll() = allSpecies
}
