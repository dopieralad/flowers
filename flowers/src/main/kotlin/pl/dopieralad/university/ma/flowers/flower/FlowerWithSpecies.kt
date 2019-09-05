package pl.dopieralad.university.ma.flowers.flower

import androidx.room.Embedded
import androidx.room.Relation
import pl.dopieralad.university.ma.flowers.species.Species

class FlowerWithSpecies(
        @Embedded
        val flower: Flower,
        @Relation(parentColumn = "speciesId", entityColumn = "id")
        val species: Species
)
