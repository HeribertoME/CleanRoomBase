package com.example.roombase.data.models

import android.os.Parcelable
import com.example.roombase.data.database.entities.StoreEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreBo(
    var id: Int,
    var storeId: String = "",
    var vtexMarketplace: String = "",
    var vtexFranchise: String = "",
    var name: String = "",
    var postalCode: String = "",
    var state: String = "",
    var city: String = "",
    var street: String = "",
    var neighborhood: String = "",
    var buildingNumber: String = "",
    var complement: String = "",
    var reference: String = "",
    var latitude: String = "",
    var longitude: String = "",
    var country: String = ""
) : Parcelable, BaseBo<StoreEntity> {

    override fun toEntity(): StoreEntity =
        StoreEntity(
            id = id,
            storeId = storeId,
            vtexMarketplace = vtexMarketplace,
            vtexFranchise = vtexFranchise,
            name = name,
            postalCode = postalCode,
            state = state,
            city = city,
            street = street,
            neighborhood = neighborhood,
            buildingNumber = buildingNumber,
            complement = complement,
            reference = reference,
            latitude = latitude,
            longitude = longitude,
            country = country)

}
