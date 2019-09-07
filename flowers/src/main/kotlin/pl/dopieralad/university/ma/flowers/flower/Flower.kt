package pl.dopieralad.university.ma.flowers.flower

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import pl.dopieralad.university.ma.flowers.species.Species
import java.io.Serializable
import java.util.Date

@Entity(
        foreignKeys = [ForeignKey(entity = Species::class, childColumns = ["speciesId"], parentColumns = ["id"])],
        indices = [Index("speciesId")]
)
data class Flower(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val name: String,
        val speciesId: Int,
        val lastWatered: Date = Date(0)
) : Serializable
