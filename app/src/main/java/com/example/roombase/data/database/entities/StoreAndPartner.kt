package com.example.roombase.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class StoreAndPartner(
    @Embedded var storeEntity: StoreEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "storeId"
    )
    var partner: PartnerEntity
)