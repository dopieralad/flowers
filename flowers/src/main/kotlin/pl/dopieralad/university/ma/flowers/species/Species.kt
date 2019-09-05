package pl.dopieralad.university.ma.flowers.species

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Species(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val name: String,
        val latinName: String,
        val wateringFrequency: Int
) : Serializable
