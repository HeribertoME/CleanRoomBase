package com.example.roombase.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roombase.data.database.entities.ShoppingCartAndOrder
import com.example.roombase.data.database.entities.ShoppingCartEntity

/**
 * ShoppingCartDao
 */
@Dao
abstract class ShoppingCartDao : BaseDao<ShoppingCartEntity> {

    /**
     * Query method to get all shopping carts
     */
    @Query("SELECT * FROM shoppingCart")
    abstract suspend fun getShoppingCarts(): List<ShoppingCartEntity>

    /**
     * Query method to get shopping cart by id
     *
     * @param id shopiing cart id value
     */
    @Transaction
    @Query("SELECT * FROM shoppingCart WHERE id = :id")
    abstract suspend fun findShoppingCartById(id: Int) : ShoppingCartEntity

    /**
     * Query method to get shopping carts and orders
     * 1:1 Relation
     */
    @Transaction
    @Query("SELECT * FROM shoppingCart")
    abstract suspend fun getShoppingCartAndOrders() : List<ShoppingCartAndOrder>

}