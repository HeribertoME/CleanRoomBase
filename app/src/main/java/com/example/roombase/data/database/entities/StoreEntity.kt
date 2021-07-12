package com.example.roombase.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roombase.data.models.StoreBo
import com.example.roombase.utils.TABLE_STORE

@Entity(tableName = TABLE_STORE)
data class StoreEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
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
) : BaseEntity<StoreBo> {

    override fun toBo(): StoreBo =
        StoreBo(
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