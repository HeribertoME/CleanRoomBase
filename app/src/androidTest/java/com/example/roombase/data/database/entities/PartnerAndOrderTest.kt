package com.example.roombase.data.database.entities

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roombase.data.database.AppDatabase
import com.example.roombase.data.database.daos.OrderDao
import com.example.roombase.data.database.daos.PartnerDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PartnerAndOrderTest : TestCase() {

    // get reference to the AppDatabase and OrderDao class
    private lateinit var db: AppDatabase
    private lateinit var partnerDao: PartnerDao
    private lateinit var orderDao: OrderDao
    private var partnerId = 0
    private var orderId = 0

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
        orderDao = db.orderDao()

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

            val order = OrderEntity(
                orderDate = "12/07/2021",
                paymentData = "Data",
                apiErrorSqs = null,
                partnerId = partnerId
            )

            orderId = orderDao.insert(order).toInt()
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
    fun getPartnersAndOrders() {
        runBlocking {
            val list = partnerDao.getPartnersAndOrders()
            assertTrue(list.isNotEmpty())
            assertEquals("mail@mail.com", list[0].partner.email)
        }
    }

}