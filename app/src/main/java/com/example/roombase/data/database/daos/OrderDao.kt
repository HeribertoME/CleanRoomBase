package com.example.roombase.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roombase.data.database.entities.OrderEntity

/**
 * OrderDao
 */
@Dao
abstract class OrderDao : BaseDao<OrderEntity> {

    /**
     * Query method to get all orders
     */
    @Query("SELECT * FROM `order`")
    abstract suspend fun getOrders(): List<OrderEntity>

    /**
     * Query method to get order by seller order id
     *
     * @param sellerOrderId seller order id value
     */
    @Transaction @Query("SELECT * FROM `order` WHERE sellerOrderId = :sellerOrderId")
    abstract suspend fun findOrderBySellerOrderId(sellerOrderId: Int) : OrderEntity
}