package com.example.roombase.data.database.entities

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roombase.data.database.AppDatabase
import com.example.roombase.data.database.daos.SessionDao
import com.example.roombase.data.database.daos.PartnerDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PartnerAndSessionTest : TestCase() {

    // get reference to the AppDatabase and SessionDao class
    private lateinit var db: AppDatabase
    private lateinit var partnerDao: PartnerDao
    private lateinit var sessionDao: SessionDao
    private var partnerId = 0
    private var sessionId = 0

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
        sessionDao = db.sessionDao()

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

            val session = SessionEntity(
                id = 1,
                accessToken = "123-ABC",
                deviceSerialNumber = "ABCD-9876",
                isPos = true,
                userName = "dev",
                partnerId = partnerId
            )

            sessionId = sessionDao.insert(session).toInt()
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
    fun getPartnersAndSessions() {
        runBlocking {
            val list = partnerDao.getPartnersAndSessions()
            assertTrue(list.isNotEmpty())
            assertEquals("mail@mail.com", list[0].partner.email)
        }
    }

}