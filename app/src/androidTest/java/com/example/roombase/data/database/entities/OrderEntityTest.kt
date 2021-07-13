package com.example.roombase.data.database.entities

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roombase.data.database.AppDatabase
import com.example.roombase.data.database.daos.OrderDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OrderEntityTest : TestCase() {

    // get reference to the AppDatabase and OrderDao class
    private lateinit var db: AppDatabase
    private lateinit var orderDao: OrderDao
    private var sellerOrderId: Int = 0

    /**
     * Override function setUp() and annotate it with @Before
     * this function will be called at first when this test class is called
     */
    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // initialize the db and dao variables
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        orderDao = db.orderDao()

        // insert values
        runBlocking {
            val order = OrderEntity(
                orderDate = "12/07/2021",
                paymentData = "Data",
                apiErrorSqs = null,
                paymentMethodId = 0
            )
            sellerOrderId = orderDao.insert(order).toInt()
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
    fun getOrders() {
        runBlocking {
            val orders = orderDao.getOrders()
            assertTrue(orders.isNotEmpty())
        }
    }

    @Test
    fun findOrderBySellerOrderId() {
        runBlocking {
            val order = orderDao.findOrderBySellerOrderId(sellerOrderId)
            assertEquals("12/07/2021", order.orderDate)
        }
    }

    @Test
    fun insertOrderList() {
        runBlocking {
            val orders = listOf(
                OrderEntity(), OrderEntity(), OrderEntity()
            )
            orderDao.insert(orders)
            val totalOrders = orderDao.getOrders()
            assert(totalOrders.size == 4)
        }
    }

    @Test
    fun updateOrder() {
        runBlocking {
            val currentOrder = orderDao.findOrderBySellerOrderId(sellerOrderId)
            currentOrder.orderDate = "13/07/2021"
            val updated = orderDao.update(currentOrder)
            assert(updated >= 1)
        }
    }

    @Test
    fun deleteOrder() {
        runBlocking {
            val order = orderDao.findOrderBySellerOrderId(sellerOrderId)
            val deleted = orderDao.delete(order)
            assertTrue(deleted >= 1)
        }
    }

}