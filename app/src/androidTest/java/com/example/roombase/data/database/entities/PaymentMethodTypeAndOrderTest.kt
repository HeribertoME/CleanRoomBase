package com.example.roombase.data.database.entities

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roombase.data.database.AppDatabase
import com.example.roombase.data.database.daos.OrderDao
import com.example.roombase.data.database.daos.PaymentMethodDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PaymentMethodTypeAndOrderTest : TestCase() {

    // get reference to the AppDatabase and OrderDao class
    private lateinit var db: AppDatabase
    private lateinit var paymentMethodDao: PaymentMethodDao
    private lateinit var orderDao: OrderDao
    private var orderId = 0
    private var paymentMethodId = 0

    /**
     * Override function setUp() and annotate it with @Before
     * this function will be called at first when this test class is called
     */
    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // initialize the db and dao variables
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        paymentMethodDao = db.paymentMethodDao()
        orderDao = db.orderDao()

        // insert values
        runBlocking {
            val paymentMethod = PaymentMethodTypeEntity(
                name = "Efectivo",
                description = "Pago en efectivo",
                image = "none",
                type = "EFE",
                isSelected = false
            )
            paymentMethodId = paymentMethodDao.insert(paymentMethod).toInt()

            val order = OrderEntity(
                orderDate = "12/07/2021",
                paymentData = "Data",
                apiErrorSqs = null,
                paymentMethodId = paymentMethodId
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
    fun getPaymentMethodTypesAndOrders() {
        runBlocking {
            val list = paymentMethodDao.getPaymentMethodTypesAndOrders()
            assertTrue(list.isNotEmpty())
            assertEquals(list[0].paymentMethodType.name, "Efectivo")
        }
    }

}