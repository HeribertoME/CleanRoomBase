package com.example.roombase.data.database

import androidx.room.TypeConverter
import com.example.roombase.data.models.ApiErrorSqs
import com.google.gson.Gson

/**
 * Room converters
 */
class Converters {

    // Example
    //@TypeConverter
    //fun objectToJson(obj: Any?): String? = Gson().toJson(obj)

    //@TypeConverter
    //fun objectFromJson(str: String): Any = Gson().fromJson(str, Any::class.java)

    /**
     * Type converter for api error object to json string
     *
     * @param apiErrorObj Api Error Object
     *
     * @return json
     */
    @TypeConverter
    fun apiErrorObjectToJson(apiErrorObj: ApiErrorSqs?): String? = Gson().toJson(apiErrorObj)

    /**
     * Type converter for api error object from json string
     *
     * @param str String json
     *
     * @return ApiErrorSqs object
     */
    @TypeConverter
    fun apiErrorObjectFromJson(str: String?): ApiErrorSqs? = Gson().fromJson(str, ApiErrorSqs::class.java)

}