package com.example.roombase.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roombase.data.models.SessionBo
import com.example.roombase.utils.TABLE_SESSION

@Entity(tableName = TABLE_SESSION)
data class SessionEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var accessToken: String? = null,
    var deviceSerialNumber: String? = null,
    var isPos: Boolean = false,
    var userName: String? = null,
    var partnerId: Int = 0
) : BaseEntity<SessionBo> {

    override fun toBo(): SessionBo =
        SessionBo(
            id = id,
            accessToken = accessToken,
            deviceSerialNumber = deviceSerialNumber,
            isPos = isPos,
            userName = userName,
            partnerId = partnerId)

}