package com.example.roombase.data.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiErrorSqs(
    @SerializedName("error")
    @Expose
    val error: String? = null,

    @SerializedName("sqsOperation")
    @Expose
    val sqsOperation: String,

    @SerializedName("sqsMessageId")
    @Expose
    val sqsMessageId: String
) : Parcelable