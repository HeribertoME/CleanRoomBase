package com.example.roombase.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ShoppingCartAndOrder(
    @Embedded var shoppingCart: ShoppingCartEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "shoppingCartId"
    )
    var order: OrderEntity
)