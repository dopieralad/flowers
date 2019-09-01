package pl.dopieralad.university.ma.flowers.flower

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FlowerDao {

    @Insert
    fun insert(flower: Flower)

    @Query("SELECT * FROM flower")
    fun getAll(): List<Flower>

    @Delete
    fun delete(flower: Flower)
}
