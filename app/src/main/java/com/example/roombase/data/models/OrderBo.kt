package com.example.roombase.data.models

import android.os.Parcelable
import com.example.roombase.data.database.entities.OrderEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderBo(
    var sellerOrderId: Int,
    var orderDate: String = "",
    var paymentData: String = "",
    var apiErrorSqs: ApiErrorSqs? = null
) : Parcelable, BaseBo<OrderEntity> {

    override fun toEntity(): OrderEntity =
        OrderEntity(
            sellerOrderId = sellerOrderId,
            orderDate = orderDate,
            paymentData = paymentData,
            apiErrorSqs = apiErrorSqs)

}