package com.example.roombase.data.database.entities

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roombase.data.database.AppDatabase
import com.example.roombase.data.database.daos.PartnerDao
import com.example.roombase.data.database.daos.StoreDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StoreAndPartnerTest : TestCase() {

    // get reference to the AppDatabase and PartnerDao class
    private lateinit var db: AppDatabase
    private lateinit var storeDao: StoreDao
    private lateinit var partnerDao: PartnerDao
    private var storeId = 0
    private var partnerId = 0

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
        partnerDao = db.partnerDao()

        // insert values
        runBlocking {
            val store = StoreEntity(
                name = "Store",
                country = "Mexico"
            )
            storeId = storeDao.insert(store).toInt()

            val partner = PartnerEntity(
                name = "Partner",
                middleName = "Middle",
                familyName = "family",
                mobilePhone = "5512345678",
                email = "mail@mail.com",
                storeId = storeId
            )

            partnerId = partnerDao.insert(partner).toInt()
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
    fun getStoresAndPartners() {
        runBlocking {
            val list = storeDao.getStoresAndPartners()
            assertTrue(list.isNotEmpty())
            assertEquals("Partner", list[0].partner.name)
        }
    }

}