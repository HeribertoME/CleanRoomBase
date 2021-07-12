package com.example.roombase.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roombase.data.database.daos.PersonDao
import com.example.roombase.data.database.entities.PersonEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) // Annotate with @RunWith
class AppDatabaseTest : TestCase() {

    // get reference to the AppDatabase and PersonDao class
    private lateinit var db: AppDatabase
    private lateinit var personDao: PersonDao

    /**
     * Override function setUp() and annotate it with @Before
     * this function will be called at first when this test class is called
     */
    @Before
    public override fun setUp() {
        // get context -- since this is an instrumental test it requires
        // context from the running application
        val context = ApplicationProvider.getApplicationContext<Context>()
        // initialize the db and dao variables
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        personDao = db.personDao()
    }

    /**
     * Override function closeDb() and annotate it with @After
     * this function will be called at last when this test class is called
     */
    @After
    fun closeDb() {
        db.close()
    }

    /**
     * Create a test function and annotate it with @Test
     * here we are first adding an item to the db and then checking if that item
     * is present in the db -- if the item is present then our test cases pass
     */
    @Test
    fun writeAndReadPerson() {
        runBlocking {
            val person = PersonEntity(
                id = 1,
                name = "Heriberto",
                age = 30,
                address = "Calle avenida, colonia, edomex"
            )
            personDao.insert(person)
            val people = personDao.getAll()
            assertTrue(people.contains(person))
        }
    }
}