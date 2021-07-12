package com.example.roombase.data.database.daos

import androidx.room.Query
import androidx.room.Transaction
import com.example.roombase.data.database.entities.PaymentMethodTypeEntity

abstract class PaymentMethodTypeDao : BaseDao<PaymentMethodTypeEntity> {

    @Query("SELECT * FROM paymentMethodType")
    abstract fun getAll(): List<PaymentMethodTypeEntity>?

    @Transaction @Query("SELECT * FROM paymentMethodType WHERE id = :id")
    abstract fun findPaymentMethodById(id: Int): PaymentMethodTypeEntity

}