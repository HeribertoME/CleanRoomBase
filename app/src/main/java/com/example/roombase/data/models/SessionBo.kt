package com.example.roombase.data.models

import android.os.Parcelable
import com.example.roombase.data.database.entities.SessionEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class SessionBo(
    var id: Int,
    var accessToken: String? = null,
    var deviceSerialNumber: String? = null,
    var isPos: Boolean,
    var userName: String? = null
) : Parcelable, BaseBo<SessionEntity> {

    override fun toEntity(): SessionEntity =
        SessionEntity(
            id = id,
            accessToken = accessToken,
            deviceSerialNumber = deviceSerialNumber,
            isPos = isPos,
            userName = userName)

}
