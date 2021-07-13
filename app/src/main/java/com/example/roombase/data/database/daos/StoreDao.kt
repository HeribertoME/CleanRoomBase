package com.example.roombase.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roombase.data.database.entities.StoreAndPartner
import com.example.roombase.data.database.entities.StoreEntity

/**
 * StoreDao
 */
@Dao
abstract class StoreDao : BaseDao<StoreEntity> {

    /**
     * Query method to get stores
     */
    @Query("SELECT * FROM store")
    abstract suspend fun getStores(): List<StoreEntity>

    /**
     * Query method to get store by id
     *
     * @param id store id value
     */
    @Query("SELECT * FROM store WHERE id = :id")
    abstract suspend fun findStoreById(id: Int): StoreEntity

    /**
     * Query method to get stores and partners
     * 1:1 Relation
     */
    @Transaction
    @Query("SELECT * FROM store")
    abstract suspend fun getStoresAndPartners() : List<StoreAndPartner>
}