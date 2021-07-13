package com.example.roombase.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roombase.data.models.ApiErrorSqs
import com.example.roombase.data.models.OrderBo
import com.example.roombase.utils.TABLE_ORDER

@Entity(tableName = TABLE_ORDER)
data class OrderEntity(
    @PrimaryKey (autoGenerate = true) var sellerOrderId: Int = 0,
    var orderDate: String = "",
    var paymentData: String = "",
    var apiErrorSqs: ApiErrorSqs? = null,
    var paymentMethodId: Int = 0,
    var shoppingCartId: Int = 0,
    var partnerId: Int = 0
) : BaseEntity<OrderBo> {

    override fun toBo(): OrderBo =
        OrderBo(
            sellerOrderId = sellerOrderId,
            orderDate = orderDate,
            paymentData = paymentData,
            apiErrorSqs = apiErrorSqs,
            paymentMethodId = paymentMethodId,
            shoppingCartId = shoppingCartId,
            partnerId = partnerId)

}