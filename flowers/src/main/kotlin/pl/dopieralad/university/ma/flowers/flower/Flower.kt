package pl.dopieralad.university.ma.flowers.flower

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Flower(
        @PrimaryKey val id: Int,
        val name: String,
        val species: String
)
