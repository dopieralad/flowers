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

    @Transaction
    @Query("""
        SELECT flower.*
        FROM flower flower
        LEFT JOIN species species
            ON flower.speciesId = species.id
        WHERE flower.lastWatered + species.wateringFrequency * 24 * 60 * 60 * 1000 < strftime('%s','now') * 1000
    """)
    fun getUnwatered(): List<Flower>

    @Delete
    fun delete(flower: Flower)
}
