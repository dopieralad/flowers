package pl.dopieralad.university.ma.flowers

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.dopieralad.university.ma.flowers.flower.Flower
import pl.dopieralad.university.ma.flowers.flower.FlowerDao

@Database(entities = arrayOf(Flower::class), version = 1)
abstract class FlowersDatabase : RoomDatabase() {

    abstract fun flowerDao(): FlowerDao
}
