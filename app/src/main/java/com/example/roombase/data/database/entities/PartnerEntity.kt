package com.example.roombase.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roombase.data.models.PartnerBo
import com.example.roombase.utils.TABLE_PARTNER
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_PARTNER)
data class PartnerEntity(
    @SerializedName("id") @PrimaryKey(autoGenerate = true) var id: Int,
    @SerializedName("name") var name: String = "",
    @SerializedName("middleName") var middleName: String = "",
    @SerializedName("familyName") var familyName: String = "",
    @SerializedName("mobilePhone") var mobilePhone: String = "",
    @SerializedName("email") var email: String = ""
) : BaseEntity<PartnerBo> {

    override fun toBo(): PartnerBo =
        PartnerBo(
            id = id,
            name = name,
            middleName = middleName,
            familyName = familyName,
            mobilePhone = mobilePhone,
            email = email)

}