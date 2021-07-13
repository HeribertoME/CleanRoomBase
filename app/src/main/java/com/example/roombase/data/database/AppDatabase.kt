package com.example.roombase.data.database

import android.content.Context
import androidx.room.*
import com.example.roombase.data.database.daos.*
import com.example.roombase.data.database.entities.*

/**
 * AppDatabase
 */
@Database(
    entities = [
        PersonEntity::class,
        OrderEntity::class,
        PaymentMethodTypeEntity::class,
        ShoppingCartEntity::class,
        PartnerEntity::class,
        StoreEntity::class,
        SessionEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "base-db"
    }

    // DAOs Declaration

    /**
     * Method to get person DAO
     *
     * @return person dao
     */
    abstract fun personDao(): PersonDao

    /**
     * Method to get order DAO
     */
    abstract fun orderDao(): OrderDao

    /**
     * Method to get payment method type DAO
     */
    abstract fun paymentMethodDao(): PaymentMethodDao

    /**
     * Method to get shopping cart DAO
     */
    abstract fun shoppingCartDao(): ShoppingCartDao

    /**
     * Method to get partner DAO
     */
    abstract fun partnerDao(): PartnerDao

    /**
     * Method to get store DAO
     */
    abstract fun storeDao(): StoreDao

    /**
     * Method to get session DAO
     */
    abstract fun sessionDao(): SessionDao

    /**
     * Configuration for tests
     */
    // Get reference of the AppDatabase and assign it null value
    @Volatile
    private var instance : AppDatabase? = null
    private val LOCK = Any()

    // create an operator fun which has context as a parameter
    // assign value to the instance variable
    operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
        instance ?: buildDatabase(context).also {
            instance = it
        }
    }

    // create a buildDatabase instance function assign the required values
    private fun buildDatabase(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        DB_NAME
    ).fallbackToDestructiveMigration().build()

}