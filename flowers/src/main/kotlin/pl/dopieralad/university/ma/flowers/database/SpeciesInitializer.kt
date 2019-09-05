package pl.dopieralad.university.ma.flowers.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import pl.dopieralad.university.ma.flowers.species.Species
import pl.dopieralad.university.ma.flowers.utils.Asynchronous

class SpeciesInitializer(val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        Asynchronous {
            val database = FlowersDatabase.getInstance(context)
            val speciesDao = database.speciesDao()

            speciesDao.insert(
                    Species(name = "Juka", latinName = "Yucca", wateringFrequency = 10),
                    Species(name = "Pieniążek", latinName = "Pilea peperomioides", wateringFrequency = 9),
                    Species(name = "Geranium", latinName = "Pelargonium graveolens", wateringFrequency = 7),
                    Species(name = "Bazylia", latinName = "Ocimum basilicum", wateringFrequency = 6),
                    Species(name = "Mięta", latinName = "Mentha", wateringFrequency = 4),
                    Species(name = "Monstera", latinName = "Monstera deliciosa", wateringFrequency = 9),
                    Species(name = "Skrzydłokwiat", latinName = "Spathiphyllum", wateringFrequency = 7),
                    Species(name = "Wrzosiec", latinName = "Erica carnea", wateringFrequency = 8),
                    Species(name = "Paprocie", latinName = "Polypodiopsida nefrolepis", wateringFrequency = 3),
                    Species(name = "Wężownica", latinName = "Sansevieria trifasciata", wateringFrequency = 21),
                    Species(name = "Eukaliptus cytrynowy", latinName = "Eucalyptus citriodora", wateringFrequency = 2),
                    Species(name = "Ołustek", latinName = "Scindapsus pictus", wateringFrequency = 10),
                    Species(name = "Palma królewska", latinName = "Phoenix canariensis", wateringFrequency = 8),
                    Species(name = "Zamiokulkas zamiolistny", latinName = "Zamioculcas zamiifolia", wateringFrequency = 8),
                    Species(name = "Sagowiec odwinięty", latinName = "Cycas revoluta", wateringFrequency = 10)
            )
        }

    }
}
