package pl.dopieralad.university.ma.flowers.species

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SpeciesDao {

    @Query("SELECT * FROM species")
    fun getAll(): LiveData<List<Species>>

    @Insert
    fun insert(vararg species: Species)
}
