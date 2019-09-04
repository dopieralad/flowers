package pl.dopieralad.university.ma.flowers.flower

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Flower(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val name: String,
        val species: String
)
