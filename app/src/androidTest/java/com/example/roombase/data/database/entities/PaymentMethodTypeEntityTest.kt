package com.example.roombase.data.database.entities

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roombase.data.database.AppDatabase
import com.example.roombase.data.database.daos.PaymentMethodDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PaymentMethodTypeEntityTest : TestCase() {

    // get reference to the AppDatabase and OrderDao class
    private lateinit var db: AppDatabase
    private lateinit var paymentMethodDao: PaymentMethodDao
    private var paymentMethodId: Int = 0

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
    fun getPaymentMethods() {
        runBlocking {
            val paymentMethods = paymentMethodDao.getPaymentMethodTypes()
            assertTrue(paymentMethods.isNotEmpty())
        }
    }

    @Test
    fun findPaymentMethodTypeById() {
        runBlocking {
            val payment = paymentMethodDao.findPaymentMethodTypeById(paymentMethodId)
            assertEquals(payment.name, "Efectivo")
        }
    }

    @Test
    fun insertPaymentMethodTypeList() {
        runBlocking {
            val paymentMethods = listOf(
                PaymentMethodTypeEntity(isSelected = false),
                PaymentMethodTypeEntity(isSelected = true)
            )
            paymentMethodDao.insert(paymentMethods)
            val total = paymentMethodDao.getPaymentMethodTypes()
            assert(total.size == 3)
        }
    }

    @Test
    fun updatePaymentMethodType() {
        runBlocking {
            val currentPaymentMethod = paymentMethodDao.findPaymentMethodTypeById(paymentMethodId)
            currentPaymentMethod.name = "Pago con Tarjeta"
            val updated = paymentMethodDao.update(currentPaymentMethod)
            assert(updated >= 1)

            val paymentMethodUpdated = paymentMethodDao.findPaymentMethodTypeById(paymentMethodId)
            assertEquals(paymentMethodUpdated.name, "Pago con Tarjeta")
        }
    }

    @Test
    fun deleteOrder() {
        runBlocking {
            val order = paymentMethodDao.findPaymentMethodTypeById(paymentMethodId)
            val deleted = paymentMethodDao.delete(order)
            assertTrue(deleted >= 1)
        }
    }

}