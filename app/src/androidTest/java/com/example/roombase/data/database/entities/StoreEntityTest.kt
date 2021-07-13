package com.example.roombase.data.database.entities

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roombase.data.database.AppDatabase
import com.example.roombase.data.database.daos.StoreDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StoreEntityTest : TestCase() {

    // get reference to the AppDatabase and OrderDao class
    private lateinit var db: AppDatabase
    private lateinit var storeDao: StoreDao
    private var storeId: Int = 0

    /**
     * Override function setUp() and annotate it with @Before
     * this function will be called at first when this test class is called
     */
    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // initialize the db and dao variables
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        storeDao = db.storeDao()

        // insert values
        runBlocking {
            val store = StoreEntity(
                name = "Store",
                country = "Mexico"
            )
            storeId = storeDao.insert(store).toInt()
        }
    }

    /**
     * Override function closeDb() and annotate it with @After
     * this function will be called at last when this test class is called
     */
    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun getStores() {
        runBlocking {
            val stores = storeDao.getStores()
            assertTrue(stores.isNotEmpty())
        }
    }

    @Test
    fun findStoreById() {
        runBlocking {
            val store = storeDao.findStoreById(storeId)
            assertEquals("Mexico", store.country)
        }
    }

    @Test
    fun insertStoreList() {
        runBlocking {
            val stores = listOf(
                StoreEntity(),
                StoreEntity(),
                StoreEntity()
            )
            storeDao.insert(stores)
            val total = storeDao.getStores()
            assert(total.size == 4)
        }
    }

    @Test
    fun updateStore() {
        runBlocking {
            val currentStore = storeDao.findStoreById(storeId)
            currentStore.city = "CDMX"
            val updated = storeDao.update(currentStore)
            assert(updated >= 1)
        }
    }

    @Test
    fun deleteStore() {
        runBlocking {
            val store = storeDao.findStoreById(storeId)
            val deleted = storeDao.delete(store)
            assertTrue(deleted >= 1)
        }
    }

}