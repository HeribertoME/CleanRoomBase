package com.example.roombase.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PaymentMethodTypeAndOrder(
    @Embedded var paymentMethodType: PaymentMethodTypeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "paymentMethodId"
    )
    var order: OrderEntity,
)