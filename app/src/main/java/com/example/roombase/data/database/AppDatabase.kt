package com.example.roombase.data.database

import android.content.Context
import androidx.room.*
import com.example.roombase.data.database.daos.PersonDao
import com.example.roombase.data.database.entities.PersonEntity

/**
 * AppDatabase
 */
@Database(
    entities = [
        PersonEntity::class,
    ], version = 1, exportSchema = false
)
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