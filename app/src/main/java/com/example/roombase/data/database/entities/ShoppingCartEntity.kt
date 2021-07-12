package com.example.roombase.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roombase.data.models.ShoppingCartBo
import com.example.roombase.utils.TABLE_SHOPPING_CART

@Entity(tableName = TABLE_SHOPPING_CART)
data class ShoppingCartEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var status: String = "",
    var total: String = "",
    var totalDiscount: String
) : BaseEntity<ShoppingCartBo> {

    override fun toBo(): ShoppingCartBo =
        ShoppingCartBo(
            id = id,
            status = status,
            total = total,
            totalDiscount = totalDiscount)

}