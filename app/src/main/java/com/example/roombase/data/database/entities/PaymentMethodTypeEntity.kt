package com.example.roombase.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roombase.data.models.PaymentMethodTypeBo
import com.example.roombase.utils.TABLE_PAYMENT_METHOD_TYPE

@Entity(tableName = TABLE_PAYMENT_METHOD_TYPE)
data class PaymentMethodTypeEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String = "",
    var description: String = "",
    var image: String = "",
    var type: String = "",
    var isSelected: Boolean
) : BaseEntity<PaymentMethodTypeBo> {

    override fun toBo(): PaymentMethodTypeBo =
        PaymentMethodTypeBo(
            id = id,
            name = name,
            description = description,
            image = image,
            type = type,
            isSelected = isSelected)

}