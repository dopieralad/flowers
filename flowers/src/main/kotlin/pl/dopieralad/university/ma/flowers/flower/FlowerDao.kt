package pl.dopieralad.university.ma.flowers.flower

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FlowerDao {

    @Insert
    fun insert(flower: Flower)

    @Transaction
    @Query("SELECT * FROM flower")
    fun getAll(): LiveData<List<FlowerWithSpecies>>

    @Delete
    fun delete(flower: Flower)
}
