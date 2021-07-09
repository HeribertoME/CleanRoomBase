package com.example.roombase.data.models

import android.os.Parcelable
import com.example.roombase.data.database.entities.CategoryTypeEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryTypeBo(
    var id: Int,
    var name: String,
    var description: String = ""
) : Parcelable, BaseBo<CategoryTypeEntity> {
    override fun toEntity(): CategoryTypeEntity {
        return CategoryTypeEntity(
            id = id,
            name = name,
            description = description
        )
    }
}