package com.example.roombase.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PartnerAndOrder(
    @Embedded var partner: PartnerEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "partnerId"
    )
    var order: OrderEntity
)