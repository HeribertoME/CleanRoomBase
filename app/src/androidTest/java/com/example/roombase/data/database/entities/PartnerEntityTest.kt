package com.example.roombase.data.database.entities

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roombase.data.database.AppDatabase
import com.example.roombase.data.database.daos.PartnerDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PartnerEntityTest : TestCase() {

    // get reference to the AppDatabase and OrderDao class
    private lateinit var db: AppDatabase
    private lateinit var partnerDao: PartnerDao
    private var partnerId: Int = 0

    /**
     * Override function setUp() and annotate it with @Before
     * this function will be called at first when this test class is called
     */
    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // initialize the db and dao variables
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        partnerDao = db.partnerDao()

        // insert values
        runBlocking {
            val partner = PartnerEntity(
                name = "Partner",
                middleName = "Middle",
                familyName = "family",
                mobilePhone = "5512345678",
                email = "mail@mail.com"
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
    fun getPartners() {
        runBlocking {
            val partners = partnerDao.getPartners()
            assertTrue(partners.isNotEmpty())
        }
    }

    @Test
    fun findPartnerById() {
        runBlocking {
            val partner = partnerDao.findPartnerById(partnerId)
            assertEquals("mail@mail.com", partner.email)
        }
    }

    @Test
    fun insertPartnerList() {
        runBlocking {
            val partners = listOf(
                PartnerEntity(),
                PartnerEntity(),
                PartnerEntity()
            )
            partnerDao.insert(partners)
            val total = partnerDao.getPartners()
            assert(total.size == 4)
        }
    }

    @Test
    fun updatePartner() {
        runBlocking {
            val currentPartner = partnerDao.findPartnerById(partnerId)
            currentPartner.mobilePhone = "5511223344"
            val updated = partnerDao.update(currentPartner)
            assert(updated >= 1)
        }
    }

    @Test
    fun deletePartner() {
        runBlocking {
            val partner = partnerDao.findPartnerById(partnerId)
            val deleted = partnerDao.delete(partner)
            assertTrue(deleted >= 1)
        }
    }

}