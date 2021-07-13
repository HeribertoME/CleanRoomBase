package com.example.roombase.data.database.entities

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roombase.data.database.AppDatabase
import com.example.roombase.data.database.daos.OrderDao
import com.example.roombase.data.database.daos.ShoppingCartDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShoppingCartAndOrderTest : TestCase() {

    // get reference to the AppDatabase and OrderDao class
    private lateinit var db: AppDatabase
    private lateinit var shoppingCartDao: ShoppingCartDao
    private lateinit var orderDao: OrderDao
    private var shoppingCartId = 0
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
        shoppingCartDao = db.shoppingCartDao()
        orderDao = db.orderDao()

        // insert values
        runBlocking {
            val shoppingCart = ShoppingCartEntity(
                status = "INI",
                total = "900",
                totalDiscount = "100"
            )
            shoppingCartId = shoppingCartDao.insert(shoppingCart).toInt()

            val order = OrderEntity(
                orderDate = "12/07/2021",
                paymentData = "Data",
                apiErrorSqs = null,
                shoppingCartId = shoppingCartId
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
    fun getShoppingCartsAndOrders() {
        runBlocking {
            val list = shoppingCartDao.getShoppingCartAndOrders()
            assertTrue(list.isNotEmpty())
            assertEquals(list[0].shoppingCart.total, "900")
        }
    }

}