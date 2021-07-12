package com.example.roombase.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roombase.data.models.ApiErrorSqs
import com.example.roombase.data.models.OrderBo
import com.example.roombase.utils.TABLE_ORDER

@Entity(tableName = TABLE_ORDER)
data class OrderEntity(
    @PrimaryKey (autoGenerate = true) var sellerOrderId: Int,
    var orderDate: String = "",
    var paymentData: String = "",
    var apiErrorSqs: ApiErrorSqs? = null
) : BaseEntity<OrderBo> {

    override fun toBo(): OrderBo =
        OrderBo(
            sellerOrderId = sellerOrderId,
            orderDate = orderDate,
            paymentData = paymentData,
            apiErrorSqs = apiErrorSqs)

}