package com.example.roombase.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roombase.data.database.entities.PaymentMethodTypeAndOrder
import com.example.roombase.data.database.entities.PaymentMethodTypeEntity

/**
 * PaymentMethodDao
 */
@Dao
abstract class PaymentMethodDao : BaseDao<PaymentMethodTypeEntity> {

    /**
     * Query method to get all payment method types
     */
    @Query("SELECT * FROM paymentMethodType")
    abstract suspend fun getPaymentMethodTypes(): List<PaymentMethodTypeEntity>

    /**
     * Query method to get order by seller order id
     *
     * @param id payment method type id value
     */
    @Transaction
    @Query("SELECT * FROM paymentMethodType WHERE id = :id")
    abstract suspend fun findPaymentMethodTypeById(id: Int) : PaymentMethodTypeEntity

    /**
     * Query method to get payment method types and orders
     * 1:1 Relation
     */
    @Transaction
    @Query("SELECT * FROM paymentMethodType")
    abstract suspend fun getPaymentMethodTypesAndOrders() : List<PaymentMethodTypeAndOrder>

}