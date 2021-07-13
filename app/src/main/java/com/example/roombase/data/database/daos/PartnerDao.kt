package com.example.roombase.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roombase.data.database.entities.PartnerAndOrder
import com.example.roombase.data.database.entities.PartnerAndSession
import com.example.roombase.data.database.entities.PartnerEntity

/**
 * PartnerDao
 */
@Dao
abstract class PartnerDao : BaseDao<PartnerEntity> {

    /**
     * Query method to get partners
     */
    @Query("SELECT * FROM partner")
    abstract suspend fun getPartners(): List<PartnerEntity>

    /**
     * Query method to get partner by id
     *
     * @param id partner id value
     */
    @Query("SELECT * FROM partner WHERE id = :id")
    abstract suspend fun findPartnerById(id: Int): PartnerEntity

    /**
     * Query method to get partners and orders
     * 1:1 Relation
     */
    @Transaction
    @Query("SELECT * FROM partner")
    abstract suspend fun getPartnersAndOrders() : List<PartnerAndOrder>

    /**
     * Query method to get partners and sessions
     * 1:1 Relation
     */
    @Query("SELECT * FROM partner")
    abstract suspend fun getPartnersAndSessions(): List<PartnerAndSession>

}