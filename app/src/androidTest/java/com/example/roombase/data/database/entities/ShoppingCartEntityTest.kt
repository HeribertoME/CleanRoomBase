package com.example.roombase.data.database.entities

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roombase.data.database.AppDatabase
import com.example.roombase.data.database.daos.ShoppingCartDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShoppingCartEntityTest : TestCase() {

    // get reference to the AppDatabase and OrderDao class
    private lateinit var db: AppDatabase
    private lateinit var shoppingCartDao: ShoppingCartDao
    private var shoppingCartId: Int = 0

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

        // insert values
        runBlocking {
            val shoppingCart = ShoppingCartEntity(
                status = "INI",
                total = "900",
                totalDiscount = "100"
            )
            shoppingCartId = shoppingCartDao.insert(shoppingCart).toInt()
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
    fun getShoppingCarts() {
        runBlocking {
            val shoppingCarts = shoppingCartDao.getShoppingCarts()
            assertTrue(shoppingCarts.isNotEmpty())
        }
    }

    @Test
    fun findShoppingCartById() {
        runBlocking {
            val shoppingCart = shoppingCartDao.findShoppingCartById(shoppingCartId)
            assertEquals("900", shoppingCart.total)
        }
    }

    @Test
    fun insertShoppingCartList() {
        runBlocking {
            val shoppingCarts = listOf(
                ShoppingCartEntity(),
                ShoppingCartEntity(),
                ShoppingCartEntity()
            )
            shoppingCartDao.insert(shoppingCarts)
            val total = shoppingCartDao.getShoppingCarts()
            assert(total.size == 4)
        }
    }

    @Test
    fun updateShoppingCart() {
        runBlocking {
            val currentShoppingCart = shoppingCartDao.findShoppingCartById(shoppingCartId)
            currentShoppingCart.total = "1000"
            val updated = shoppingCartDao.update(currentShoppingCart)
            assert(updated >= 1)
        }
    }

    @Test
    fun deleteShoppingCart() {
        runBlocking {
            val shoppingCart = shoppingCartDao.findShoppingCartById(shoppingCartId)
            val deleted = shoppingCartDao.delete(shoppingCart)
            assertTrue(deleted >= 1)
        }
    }

}