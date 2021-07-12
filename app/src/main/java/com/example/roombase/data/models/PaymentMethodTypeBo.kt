package com.example.roombase.data.models

import android.os.Parcelable
import com.example.roombase.data.database.entities.PaymentMethodTypeEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentMethodTypeBo(
    var id: Int,
    var name: String = "",
    var description: String = "",
    var image: String = "",
    var type: String = "",
    var isSelected: Boolean
) : Parcelable, BaseBo<PaymentMethodTypeEntity> {

    override fun toEntity(): PaymentMethodTypeEntity =
        PaymentMethodTypeEntity(
            id = id,
            name = name,
            description = description,
            image= image,
            type = type,
            isSelected = isSelected)

}