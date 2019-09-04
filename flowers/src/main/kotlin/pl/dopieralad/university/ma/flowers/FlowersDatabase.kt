package pl.dopieralad.university.ma.flowers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.dopieralad.university.ma.flowers.flower.Flower
import pl.dopieralad.university.ma.flowers.flower.FlowerDao

@Database(entities = arrayOf(Flower::class), version = 1)
abstract class FlowersDatabase : RoomDatabase() {

    abstract fun flowerDao(): FlowerDao

    companion object {

        private lateinit var INSTANCE: FlowersDatabase

        fun getInstance(context: Context): FlowersDatabase {
            if (::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, FlowersDatabase::class.java, "flowers-database").build()
            }

            return INSTANCE
        }
    }
}
