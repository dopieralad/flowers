package pl.dopieralad.university.ma.flowers.flower

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.dopieralad.university.ma.flowers.database.FlowersDatabase
import pl.dopieralad.university.ma.flowers.utils.observeOnce

class FlowerDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: FlowersDatabase
    private lateinit var flowerDao: FlowerDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, FlowersDatabase::class.java).build()
        flowerDao = database.flowerDao()
    }

    @Test
    fun getsFlowers() {
        // When
        val flowers = flowerDao.getAll()

        // Then
        flowers.observeOnce {
            assertThat(it, notNullValue())
            assertThat(it.isEmpty(), equalTo(true))
        }
    }

    @Test
    fun insertsFlower() {
        // Given
        val flower = Flower(1, "Monstera", "")

        // When
        flowerDao.insert(flower)

        // Then
        flowerDao.getAll().observeOnce { flowers ->
            assertThat(flowers, notNullValue())
            assertThat(flowers.size, equalTo(1))

            flowers[0].also { it ->
                assertThat(it.id, equalTo(flower.id))
                assertThat(it.name, equalTo(flower.name))
                assertThat(it.species, equalTo(flower.species))
            }
        }

    }

    @Test
    fun deletesFlower() {
        // Given
        val flower = Flower(1, "Monstera", "")

        // When
        flowerDao.insert(flower)
        flowerDao.delete(flower)

        // Then
        flowerDao.getAll().observeOnce { flowers ->
            assertThat(flowers, notNullValue())
            assertThat(flowers.size, equalTo(0))
        }
    }

    @After
    fun teardown() {
        database.close()
    }
}
