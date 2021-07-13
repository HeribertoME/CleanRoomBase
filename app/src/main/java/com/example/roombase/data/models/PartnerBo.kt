package com.example.roombase.data.models

import android.os.Parcelable
import com.example.roombase.data.database.entities.PartnerEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class PartnerBo(
    var id: Int,
    var name: String = "",
    var middleName: String = "",
    var familyName: String = "",
    var mobilePhone: String = "",
    var email: String = "",
    var storeId: Int = 0
) : Parcelable, BaseBo<PartnerEntity> {

    override fun toEntity(): PartnerEntity =
        PartnerEntity(
            id = id,
            name = name,
            middleName = middleName,
            familyName = familyName,
            mobilePhone = mobilePhone,
            email = email,
            storeId = storeId)

}