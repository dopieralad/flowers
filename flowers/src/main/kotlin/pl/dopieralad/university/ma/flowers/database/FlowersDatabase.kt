package pl.dopieralad.university.ma.flowers.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.dopieralad.university.ma.flowers.flower.Flower
import pl.dopieralad.university.ma.flowers.flower.FlowerDao
import pl.dopieralad.university.ma.flowers.species.Species
import pl.dopieralad.university.ma.flowers.species.SpeciesDao

@Database(entities = [Flower::class, Species::class], version = 1)
@TypeConverters(Converters::class)
abstract class FlowersDatabase : RoomDatabase() {

    abstract fun flowerDao(): FlowerDao

    abstract fun speciesDao(): SpeciesDao

    companion object {

        private lateinit var INSTANCE: FlowersDatabase

        fun getInstance(context: Context): FlowersDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                INSTANCE = Room
                        .databaseBuilder(context, FlowersDatabase::class.java, "flowers-database")
                        .addCallback(SpeciesInitializer(context))
                        .build()
            }

            return INSTANCE
        }
    }
}
