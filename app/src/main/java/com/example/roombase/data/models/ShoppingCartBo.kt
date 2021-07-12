package com.example.roombase.data.models

import android.os.Parcelable
import com.example.roombase.data.database.entities.ShoppingCartEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShoppingCartBo(
    var id: Int,
    var status: String = "",
    var total: String = "",
    var totalDiscount: String
) : Parcelable, BaseBo<ShoppingCartEntity> {

    override fun toEntity(): ShoppingCartEntity =
        ShoppingCartEntity(
            id = id,
            status = status,
            total = total,
            totalDiscount = totalDiscount)

}